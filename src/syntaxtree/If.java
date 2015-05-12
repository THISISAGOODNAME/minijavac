package syntaxtree;
import java.util.Hashtable;
import java.lang.Math;
import symboltree.*;
// import Tree.*;
// import temp.Label;

public class If extends Statement{

    private Exp e;
    private Statement s1;
    private Statement s2;

    public If(Exp exp, Statement st1, Statement st2){
    	e=exp;
    	s1=st1;
    	s2=st2;
    }
    
    public String toString() {
        return String.format("if(%s) {%s} else {%s}", e.toString(),s1.toString(),s2.toString());
    }
    
    public String strRepr(int level) {
        String elseStm = (s2==null)?"":String.format("%s<else>\n%s%s</else>\n",
            tab(level+1), s2.strRepr(level+2), tab(level+1));
        return String.format(
            "%s<if>\n%s<condition>\n%s%s</condition>\n%s<then>\n%s%s<then>\n%s%s</if>\n",
            tab(level), tab(level+1), e.strRepr(level+2), tab(level+1),
            tab(level+1), s1.strRepr(level+2), tab(level+1), elseStm, tab(level));
    }

    public boolean typeCheck(Hashtable symbolTrees, String currentClass, String currentMethod) throws TypeException {
        if (!e.getType(symbolTrees, currentClass, currentMethod).equals(new BooleanType())) {
            throw new TypeException("Boolean expression required. " + e.getPosition());
        }
        if (s2 == null) {
            return (s1.typeCheck(symbolTrees, currentClass, currentMethod));
        } else {
            return (s1.typeCheck(symbolTrees, currentClass, currentMethod) &&
                    s2.typeCheck(symbolTrees, currentClass, currentMethod));
        }
    }
    
//     public Stm translate() {
//         Tree.Exp b = e.translate();
//         
//         Label t = new Label();
//         LABEL t_lab = new LABEL(t);
//         NAME t_name = new NAME(t);
//         Stm t_stm = s1.translate();
//         
//         Label f = new Label();
//         LABEL f_lab = new LABEL(f);
//         NAME f_name = new NAME(f);
//         Stm f_stm = s2.translate();
//         
//         Label end = new Label();
//         LABEL end_lab = new LABEL(end);
//         NAME end_name = new NAME(end);
//         
//         CJUMP ifThenElse = new CJUMP(CJUMP.EQ,b,new CONST(1),t_name,f_name);
//         return new SEQ(ifThenElse,
//                         new SEQ(t_lab,
//                             new SEQ(t_stm,
//                                 new SEQ(new JUMP(end_name),
//                                     new SEQ(f_lab,
//                                         new SEQ(f_stm,end_lab))))));
//     }
    
    public String generateJVM(MethodNode method) {
        String jvmCode = e.generateFetchJVM(method);
        Label t = new Label();
        Label end = new Label();
        if (s2 != null) {
            Label f = new Label();
            jvmCode += String.format("ldc 1\nisub\nifeq %s\ngoto %s\n%s:\n%sgoto %s\n%s:\n%s%s:\n",
                t, f, t, s1.generateJVM(method), end, f, s2.generateJVM(method), end);
        } else {
            jvmCode += String.format("ldc 1\nisub\nifeq %s\ngoto %s\n%s:\n%s\n%s:\n",
                t, end, t, s1.generateJVM(method), end);
        }
        return jvmCode;    
    }
    
    public int maxDepth(int methodDepth, Hashtable<String,MethodNode> symboltrees) {
        if (s2 == null) {
            return Math.max(Math.max(2, e.maxDepth(methodDepth, symboltrees)),
                            s1.maxDepth(methodDepth, symboltrees));
        } else {
            return Math.max(Math.max(2, e.maxDepth(methodDepth, symboltrees)),
                Math.max(s1.maxDepth(methodDepth, symboltrees), s2.maxDepth(methodDepth, symboltrees)));
        }
    }
}