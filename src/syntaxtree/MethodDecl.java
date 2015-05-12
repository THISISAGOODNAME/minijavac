package syntaxtree;
import java.util.Hashtable;
import java.util.ArrayList;
import symboltree.*;
// import Tree.*;
// import temp.Label;

public class MethodDecl extends MiniJavaParserToken {

    private Type t;
    private Identifier i;
    private VarDeclList formallist,vardecllist;
    private StatementList stmlist;
    private Exp e;
    private Type classType = null;
    boolean main;
    boolean isStatic = false;
    ArrayList<Type> parameterTypes = null;
    MethodNode mn;
    static int maxMethodDepth = 15;
    
    public MethodDecl(Type ty, Identifier id, VarDeclList f, VarDeclList v,
                      StatementList s, Exp ex, boolean m){
    	t=ty;
    	i=id;
    	formallist=f;
    	vardecllist=v;
    	stmlist=s;
    	e=ex;
    	main = m;
    	isStatic = main;
    }

    public String toString() {
        return String.format("public %s %s(%s) {%s%s%sreturn %s;}",
                             t.toString(), i.toString(), formallist.toString(),
                             vardecllist.toString(), stmlist.toString(), e.toString());
    }
    
    public String strRepr(int level) {
        return String.format(
            "%s<method name=%s type=%s>\n%s%s%s%s<return>\n%s%s</return>\n%s</method>\n",
            tab(level), t, i, formallist.paramStrRepr(level+1), vardecllist.strRepr(level+1),
            stmlist.strRepr(level+1), tab(level+1), e.strRepr(level+2), tab(level+1), tab(level));
    }

    public String name() {
        return i.toString();
    }
    
    public Type type() {
        return t;
    }
    
    public VarDeclList getFormalList() {
        return formallist;
    }
    
    public VarDeclList getVarDeclList() {
        return vardecllist;
    }
    
    public StatementList getStatementList() {
        return stmlist;
    }
    
    public Exp getReturnExp(){
        return e;
    }
    
    public MethodNode getMethodNode(ClassDecl cd) {
        if (mn == null) mn = new MethodNode(this, cd);
        return mn;
    }
    
    public boolean isStatic() {
        return isStatic;
    }
    
    public boolean typeCheck(Hashtable symbolTrees, String currentClass, String currentMethod)
        throws TypeException {
        Type eType = e.getType(symbolTrees, currentClass, currentMethod);
        if (!eType.equals(t, symbolTrees))
            throw new TypeException(
                String.format("Found %s, required %s. %s", eType, t, e.getPosition()));
            
        return true;
    }
    
//     public Tree.Stm translate() {
//         String fun_desc = String.format("%s.%s(%s)", classType, i, formallist.getTypesString());
//         LABEL m_label = new LABEL(new Label(fun_desc));
//         SEQ fun_body = new SEQ(m_label, stmlist.translate());
//         return new SEQ(fun_body, new Tree.RET(e.translate()));
//     }
    
    public String generateJVM(Hashtable<String, MethodNode> symboltrees) {
        String stat = "";
        String paramStr;
        String typeStr;
        String expStr;
        String returnTypeString;
        int numbOfParams;
        if (main) {
            stat = "static ";
            paramStr = "[Ljava/lang/String;";
            numbOfParams = 1;
            typeStr = "V";
            expStr = "";
            returnTypeString = "";
        } else {
            paramStr = formallist.getJVMTypeString();
            numbOfParams = formallist.size() + 1;
            typeStr = t.jvmType();
            expStr = e.generateFetchJVM(mn);
            returnTypeString = (t instanceof IdentifierType ||
                                t instanceof IntArrayType)?"a":"i";
        }
        int numbOfDecl = vardecllist.size();
        int locals = numbOfParams + numbOfDecl;
        int stack = maxDepth(1, symboltrees);
        String declStr = ""; //vardecllist.generateJVM(numbOfParams);
        String stmStr = stmlist.generateJVM(mn);
        String jvmCode = String.format(
            ".method public %s%s(%s)%s\n.limit locals %d\n.limit stack %d\n"+
            "%s%s%s%sreturn\n.end method\n",
                stat, i, paramStr, typeStr, locals, stack,
                declStr, stmStr, expStr, returnTypeString);
        return jvmCode;            
    }
    
    public int maxDepth(int methodDepth, Hashtable<String, MethodNode> symboltrees) {
        if (methodDepth >= maxMethodDepth) return 0;// Limit number of function calls on stack.
        return Math.max(stmlist.maxDepth(methodDepth, symboltrees),
                        e.maxDepth(methodDepth, symboltrees));
    }
    
    public boolean equals(MethodDecl othermd) {
        if (!name().equals(othermd.name())) return false;
        VarDeclList otherformallist = othermd.getFormalList();
        for (int i=0; i<formallist.size(); i++) {
            VarDecl vd = formallist.elementAt(i);
            VarDecl othervd = (otherformallist.size() > i )?otherformallist.elementAt(i):null;
            if (!vd.equals(othervd)) return false;
        }
        return type().equals(othermd.type());
    }
}