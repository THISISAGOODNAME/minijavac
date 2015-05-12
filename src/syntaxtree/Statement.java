package syntaxtree;
import java.util.Hashtable;
import symboltree.*;
// import irtree.Tree.*;

public abstract class Statement extends MiniJavaParserToken {
    
    public Statement(){}
    
    public abstract boolean typeCheck(Hashtable symbolTrees, String currentClass, String currentMethod) throws TypeException;
    
//     public abstract Tree.Stm translate();

    public abstract String generateJVM(MethodNode method);
    
    public abstract int maxDepth(int methodDepth, Hashtable<String, MethodNode> symboltrees);
}