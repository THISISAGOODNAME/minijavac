package syntaxtree;
import java.util.Hashtable;
import symboltree.*;
// import Tree.*;

public class Block extends Statement {
    
    private StatementList list;

    public Block(StatementList l){
	list=l;
    }

    public String toString() {
        return "{" + list.toString() + "}";
    }
    
    public String strRepr(int level) {
        return list.strRepr(level);
    }
    
    public boolean typeCheck(Hashtable symbolTrees, String currentClass, String currentMethod) throws TypeException{
        return list.typeCheck(symbolTrees, currentClass, currentMethod);
    }
    
//     public Stm translate() {
//         return list.translate();
//     }

    public String generateJVM(MethodNode method) {
        return list.generateJVM(method);
    }
    
    public int maxDepth(int methodDepth, Hashtable<String,MethodNode> symboltrees) {
        return list.maxDepth(methodDepth, symboltrees);
    }
}