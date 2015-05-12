package syntaxtree;
import java.util.Hashtable;
import java.lang.Math;
import symboltree.*;
// import Tree.*;
// import temp.Label;

public class While extends Statement{

    private Exp e;
    private Statement s1;

    public While(Exp exp, Statement st1){
    	e=exp;
    	s1=st1;
    }
    
    public String strRepr(int level) {
        return String.format(
            "%s<while>\n%s<condition>\n%s%s</condition>\n%s<do>\n%s%s<do>\n%s</while>\n",
            tab(level), tab(level+1), e.strRepr(level+2), tab(level+1),
            tab(level+1), s1.strRepr(level+2), tab(level+1), tab(level));
    }
    
    public boolean typeCheck(Hashtable symbolTrees, String currentClass, String currentMethod) throws TypeException {
        if (!e.getType(symbolTrees, currentClass, currentMethod).equals(new BooleanType())) {
            throw new TypeException("Boolean expression required. " + e.getPosition());
        }
        return (s1.typeCheck(symbolTrees, currentClass, currentMethod));
    }
    
//     public Tree.Stm translate() {
//         Tree.Exp b = e.translate();
//         
//         Label t = new Label();
//         Tree.LABEL t_lab = new Tree.LABEL(t);
//         Tree.NAME t_name = new Tree.NAME(t);
//         Tree.Stm t_stm = s1.translate();
//         
//         Label end = new Label();
//         Tree.LABEL end_lab = new Tree.LABEL(end); //stm
//         Tree.NAME end_name = new Tree.NAME(end); //exp
//         
//         Tree.CJUMP ifThenElse = new Tree.CJUMP(CJUMP.EQ,b,new Tree.CONST(1),t_name,end_name);
//         return new Tree.SEQ(ifThenElse,
//                             new Tree.SEQ(t_lab,
//                                 new Tree.SEQ(t_stm,
//                                     new Tree.SEQ(ifThenElse,end_lab))));
//     }
    
    public String generateJVM(MethodNode method) {
        Label t = new Label();
        Label end = new Label();
        String jvmCode = String.format(
            "%siconst_1\nisub\nifeq %s\ngoto %s\n%s:\n%s%siconst_1\nisub\nifeq %s\n%s:\n",
            e.generateFetchJVM(method), t, end, t, s1.generateJVM(method),
            e.generateFetchJVM(method), t, end);
        return jvmCode;
    }
    
    public int maxDepth(int methodDepth, Hashtable<String, MethodNode> symboltrees) {
        return Math.max(Math.max(2, e.maxDepth(methodDepth, symboltrees)), s1.maxDepth(methodDepth, symboltrees));
    }
}