package syntaxtree;
import java.util.Hashtable;
import symboltree.*;
// import irtree.Tree.*;

public class ArrayLength extends Exp{

    private Exp e1;

    public ArrayLength(){}
        
    public ArrayLength(Exp exp1){
	e1=exp1;
    }

    public String toString() {
        return "(" + e1.toString() + ").length";
    }
    
    public String strRepr(int level) {
        return String.format("%s<array_length>\n%s%s</array_length>\n",
            tab(level), e1.strRepr(level+1), tab(level));
    }
    
    public Type getType(Hashtable symbolTrees, String currentClass, String currentMethod) throws TypeException {
        if (!e1.getType(symbolTrees, currentClass, currentMethod).equals(new IntArrayType())) {
            throw new TypeException("Boolean expression required. " + getPosition());
        }
        return new IntegerType();
    }
    
//     public Tree.Exp translate() {
//         return new Tree.MEM(e1.translate());
//     }
    
    public String generateFetchJVM(MethodNode method) {
        return String.format("%sarraylength\n", e1.generateFetchJVM(method));
    }
    
    public String generateStoreJVM(MethodNode method) {
        return "";
    }
    
    public int maxDepth(int methodDepth, Hashtable<String,MethodNode> symboltrees) {
        return e1.maxDepth(methodDepth, symboltrees);
    }
}