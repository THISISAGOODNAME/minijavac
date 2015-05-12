package syntaxtree;
import java.util.Hashtable;
import symboltree.*;
// import Tree.*;
// import temp.Label;

public class NewArray extends Exp{

    private Exp e;

    public NewArray(Exp exp) {
	    e=exp;
    }
    
    public String strRepr(int level) {
        return String.format("%s<new_array>\n%s<size>\n%s%s</size>\n%s</new_array>\n",
            tab(level), tab(level+1), e.strRepr(level+2), tab(level+1), tab(level));
    }
    
    public Type getType(Hashtable symbolTrees, String currentClass, String currentMethod) {
        return new IntArrayType();
    }
    
//     public Tree.Exp translate() {
//         Tree.Exp size = e.translate();
//         Label label = new Label("_MiniJavaLib_initArray(int numberOfElements)");
//         Tree.ExpList params = new Tree.ExpList(size, null);
//         return new Tree.CALL(new Tree.NAME(label), params);
//     }
    
    public String generateFetchJVM(MethodNode method) {
        String jvmCode = String.format("%snewarray int\n", e.generateFetchJVM(method));
        return jvmCode;
    }
    
    public String generateStoreJVM(MethodNode method) {
        return "";
    }
    
    public int maxDepth(int methodDepth, Hashtable<String, MethodNode> symboltrees) {
        return e.maxDepth(methodDepth, symboltrees);
    }
}