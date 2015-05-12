package syntaxtree;
import java.util.Hashtable;
import symboltree.*;
// import Tree.*;

public abstract class BoolComp extends Binop {
    int treeBinop;

    public BoolComp(Exp exp1, Exp exp2, String o) {
    	super(exp1, exp2, o);
    }
    
    public Type getType(Hashtable symbolTrees, String currentClass, String currentMethod)
        throws TypeException {
        BooleanType bt = new BooleanType();
        if (!e1.getType(symbolTrees, currentClass, currentMethod).equals(bt)) {
            throw new TypeException(String.format("%s requires boolean type. %s", op, e1.getPosition()));
        }
        if (!e2.getType(symbolTrees, currentClass, currentMethod).equals(bt)) {
            throw new TypeException(String.format("%s requires boolean type. %s", op, e2.getPosition()));
        }
        return bt;
    }
    
//     public Tree.Exp translate() {
//         Tree.Exp b1 = e1.translate();
//         Tree.Exp b2 = e2.translate();
//         return new Tree.BINOP(treeBinop, b1, b2);
//     }
}