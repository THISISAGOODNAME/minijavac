package syntaxtree;
import java.util.Hashtable;
import symboltree.*;
// import Tree.*;

public class This extends Exp{

    public This() {}
    
    public This(int l, int c) {
        setPosition(l,c);
    }
    
    public String toString() {
        return "this";
    }
    
    public String strRepr(int level) {
        return String.format("%s<this>\n", tab(level));
    }
    
    public Type getType(Hashtable symbolTrees, String currentClass, String currentMethod) throws TypeException {
        return new IdentifierType(currentClass);
    }
    
    public String generateFetchJVM(MethodNode method) {
        return "aload_0\n";
    }
    
    public String generateStoreJVM(MethodNode method) {
        return "astore_0\n";
    }
    
    public int maxDepth(int methodDepth, Hashtable<String, MethodNode> symboltrees) {
        return 1;
    }
//     
//     public Tree.Exp translate() { return null; }
}