package syntaxtree;
import java.util.Hashtable;
import symboltree.*;
// import Tree.*;
// import temp.Label;

public class Comp extends Binop {
    String desc;

    public Comp(Exp exp1, Exp exp2, String o) {
    	super(exp1, exp2, o);
    	desc = opToDesc.get(o);
    }
    
    public Type getType(Hashtable symbolTrees, String currentClass, String currentMethod)
        throws TypeException {
        IntegerType it = new IntegerType();
        if (!e1.getType(symbolTrees, currentClass, currentMethod).equals(it)) {
            throw new TypeException(String.format("%s requires boolean type. %s", op, e1.getPosition()));
        }
        if (!e2.getType(symbolTrees, currentClass, currentMethod).equals(it)) {
            throw new TypeException(String.format("%s requires boolean type. %s", op, e2.getPosition()));
        }
        return new BooleanType();
    }
    
//     public Tree.Exp translate() {
//         Tree.Exp b1 = e1.translate();
//         Tree.Exp b2 = e2.translate();
//         return new Tree.BINOP(treeBinop, b1, b2);
//     }
    
    public String generateFetchJVM(MethodNode method) {
        Label t = new Label();
        Label end = new Label();
        return String.format(
            "%s%sif_icmp%s %s\niconst_0\ngoto %s\n%s:\niconst_1\n%s:\n",
            e1.generateFetchJVM(method), e2.generateFetchJVM(method), desc, t, end, t, end);
    }
}