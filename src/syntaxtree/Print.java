package syntaxtree;
import java.util.Hashtable;
import symboltree.*;
// import temp.Label;
// import Tree.*;

public class Print extends Statement{

    private Exp e;
    Type type ;
    String className;

    public Print(){}

    public Print(Exp exp){
	e=exp;
    }

    public String toString() {
        return "print(" + e + ")";
    }
    
    public String strRepr(int level) {
        return String.format("%s<print>\n%s%s</print>\n",
            tab(level), e.strRepr(level+1), tab(level));
    }
    
    public boolean typeCheck(Hashtable symbolTrees, String currentClass, String currentMethod)
        throws TypeException{
        className = currentClass;
        type = e.getType(symbolTrees, currentClass, currentMethod);
        if (type instanceof IdentifierType) throw new TypeException(String.format(
        	"Cannot print expression of type %s. %s", type, e.getPosition()));
        return (type != null);
    }
    
    public String generateJVM(MethodNode method) {
        String jvmCode = "";
        String addr = e.generateFetchJVM(method);
        if (type instanceof IntegerType ) {
            jvmCode += String.format("%sinvokestatic %s.print(I)V\n", addr, className);
        } else if (type instanceof IntArrayType) {
            Label t = new Label();
            Label end = new Label();
            jvmCode += String.format(
                "iconst_0\n%sarraylength\nifeq %s\n"+
                "%s:\ndup\n%sswap\niaload\ninvokestatic %s.print(I)V\n"+
                "ldc \" \"\ninvokestatic %s.print(Ljava/lang/String;)V\n"+
                "iconst_1\niadd\ndup\n%sarraylength\nisub\niflt %s"+
                "\n%s:\npop\n",
                addr, end, t, addr, className, className, addr, t, end);
        } else if (type instanceof BooleanType) {
            Label t = new Label();
            Label f = new Label();
            Label end = new Label();
            jvmCode += String.format(
                "%sifeq %s\n%s:\nldc \"true\"\ngoto %s\n%s:\nldc \"false\"\n%s:"+
                "\ninvokestatic %s.print(Ljava/lang/String;)V\n",
                addr, f, t, end, f, end, className);
        } else {
            jvmCode += String.format(
                "ldc \"%s\"\ninvokestatic %s.print(Ljava/lang/String;)V\n",
                type.toString(), className);
        }
        jvmCode += String.format(
            "ldc \"\\n\"\ninvokestatic %s.print(Ljava/lang/String;)V\n", className);
        return jvmCode;
    }
    
    public int maxDepth(int methodDepth, Hashtable<String, MethodNode> symboltrees) {
        if (type instanceof IntegerType) {
            return Math.max(6, e.maxDepth(methodDepth, symboltrees));
        } else if (type instanceof IntArrayType) {
            return Math.max(7, 2 + e.maxDepth(methodDepth, symboltrees));
        } else if (type instanceof BooleanType) {
            return Math.max(6, e.maxDepth(methodDepth, symboltrees));
        } else {
            return 6;
        }
    }
    
//     public Stm translate() { return null; }
}