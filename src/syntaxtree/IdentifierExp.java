package syntaxtree;
import java.util.Hashtable;
import symboltree.*;
// import temp.Label.*;

public class IdentifierExp extends Exp{

    private String s;
    Identifier i;
    Type t;

    public IdentifierExp(String st) {
	    i = new Identifier(st);
	    s=i.toString();
    }

    public String toString(){
	    return s;
    }

    public String strRepr(int level) {
        return String.format("%s<identifier name=%s/>\n", tab(level), s);
    }

    public Type getType(Hashtable symbolTrees, String currentClass, String currentMethod)
        throws TypeException {
        MethodNode methodSymbolTree = (MethodNode)symbolTrees.get(currentClass + "." + currentMethod);
        Node variable = methodSymbolTree.getMethodVariable(s);
        if (variable == null) throw new TypeException(
            String.format("Variable %s not declared. %s", s, getPosition()));
        t = variable.getType();
        return t;
    }
    
//     public Tree.Exp translate(){
//         return translateToTemp();
//     }
    
//     public Tree.TEMP translateToTemp(){
//         return new Tree.TEMP(s);
//     }
    
    public String generateFetchJVM(MethodNode method) {
        return generateJVM(method, false);
    }

    public String generateStoreJVM(MethodNode method) {
        return generateJVM(method, true);
    }
    
    public String generateJVM(MethodNode method, boolean store) {
        String store_or_load = store?"store":"load";
        String type = ((t instanceof IdentifierType| t instanceof IntArrayType)? "a":"i") +
            store_or_load;
        String jvmCode;
        Node var = null;
        if (method.getRight() != null) var = method.getRight().getNode(s);
        if (var != null) {
            jvmCode = String.format("%s %s\n", type, var.getVarNumb());
        } else {
//             System.out.println("Find node: " + s);
            var = method.getClassVariable(s);
            String className = method.getClassName();
            String swap = store?"swap\n":"";
            jvmCode = String.format("aload_0\n%s%sfield %s/%s %s\n",
                swap, (store?"put":"get"),
                className, var.getKey(), var.getType().jvmType());
        }
        return jvmCode;
    }
    
    public int maxDepth(int methodDepth, Hashtable<String,MethodNode> symboltrees) {
        return 2;
    }
}