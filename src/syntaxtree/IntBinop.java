package syntaxtree;
import java.util.Hashtable;
import symboltree.*;
// import Tree.*;

public class IntBinop extends Binop {
    String desc;
    
    public IntBinop(Exp exp1, Exp exp2, String o) {
    	super(exp1, exp2, o);
    	desc = opToDesc.get(o);
    }
    
    public Type getType(Hashtable symbolTrees, String currentClass, String currentMethod)
        throws TypeException {
        IntegerType it = new IntegerType();
        Type e1type = e1.getType(symbolTrees, currentClass, currentMethod);
        Type e2type = e2.getType(symbolTrees, currentClass, currentMethod);
        if (!e1type.equals(it)) {
            throw new TypeException(String.format("%s requires integer type. Found %s. %s",
                op, e1type, e1.getPosition()));
        }
        if (!e2type.equals(it)) {
            throw new TypeException(String.format("%s requires integer type. Found %s. %s",
            op, e2type, e2.getPosition()));
        }
        return it;
    }
    
//     public Tree.Exp translate() {
//         return new Tree.BINOP(treeBinop, e1.translate(), e2.translate());
//     }
    
    public String generateFetchJVM(MethodNode method) {
        return String.format(
            "%s%si%s\n", e1.generateFetchJVM(method), e2.generateFetchJVM(method), desc);
    }
    
    public String generateStoreJVM(MethodNode method) {
        return "";
    }
    
}