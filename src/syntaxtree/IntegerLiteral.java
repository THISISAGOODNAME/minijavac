package syntaxtree;
import java.util.Hashtable;
import symboltree.*;
// import Tree.*;

public class IntegerLiteral extends Exp{

    private int i;

    public IntegerLiteral(){}

    public IntegerLiteral(int number){
	i = number;
    }

    public String toString() {
        return "" + Integer.toString(i);
    }
    
    public String strRepr(int level) {
        return String.format("%s<constant value=%d/>\n", tab(level), i);
    }
    
    public Type getType(Hashtable symbolTrees, String currentClass, String currentMethod) {
        return new IntegerType();
    }
    
//     public Tree.Exp translate(){
//         return new Tree.CONST(i);
//     }
    
    public String generateFetchJVM(MethodNode method) {
	String pref;
	if(i==-1)
	    return String.format("iconst_m1\n");
	else if(0 <= i && i <= 5)
	    pref = "iconst_";
	else if(i <= 128 && -127 <= i)
	    pref = "bipush ";
	else if(i <= 32767 && -32768 <= i)
	    pref = "sipush ";
	else
	    pref = "ldc ";
        return String.format("%s%d\n", pref, i);
    }
    
    public String generateStoreJVM(MethodNode method) {
        return "";
    }
    
    public int maxDepth(int methodDepth, Hashtable<String, MethodNode> symboltrees) {
        return 1;
    }
}