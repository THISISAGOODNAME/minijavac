package syntaxtree;
import java.util.Hashtable;
import symboltree.*;
// import Tree.*;
// import temp.Label;

public class NewObject extends Exp{

    private Identifier i;

    public NewObject(){}
    
    public NewObject(Identifier id){
	i=id;
    }

    public String toString() {
        return "new (" + i + "())";
    }
    
    public String strRepr(int level) {
        return String.format("%s<new_object type=%s/>\n", tab(level), i);
    }
    
    public Type getType(Hashtable symbolTrees, String currentClass, String currentMethod)
    throws TypeException {
	    i.typeCheck(symbolTrees);
        return new IdentifierType(i.toString());
    }
    
//     public Tree.Exp translate() {
//         Label label = new Label("_MiniJavaLib_initObject(size_t sizeInBytes)");
//         return new CALL(new NAME(label), new Tree.ExpList());
//     }
    
    public String generateFetchJVM(MethodNode method) {
        return String.format("new %s\ndup\ninvokespecial %s/<init>()V\n", i, i);
    }
    
    public String generateStoreJVM(MethodNode method) {
        return "";
    }
    
    public int maxDepth(int methodDepth, Hashtable<String, MethodNode> symboltrees) {
        return 2;
    }
}