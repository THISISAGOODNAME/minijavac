package syntaxtree;
import java.util.Hashtable;
import symboltree.*;
// import Tree.*;

public class Not extends Exp{

    private Exp e;

    public Not(){}

    public Not(Exp exp){
	e=exp;
    }

    public String toString() {
        return "!(" + e + ")";
    }
    
    public String strRepr(int level) {
        return String.format("%s<not>\n%s%s</not>\n",
            tab(level), e.strRepr(level+1), tab(level));
    }
    
    public Type getType(Hashtable symbolTrees, String currentClass, String currentMethod) throws TypeException {
        BooleanType bt = new BooleanType();
        if (e.getType(symbolTrees, currentClass, currentMethod).equals(bt))
            return bt;
        else throw new TypeException(String.format("! requires boolean type. %s", e.getPosition()));
    }
    
    public String generateFetchJVM(MethodNode method) {
        String jvmCode = String.format("ldc 1\n%sisub\n", e.generateFetchJVM(method));
        return jvmCode;
    }
    
    public String generateStoreJVM(MethodNode method) {
        return "";
    }
    
    public int maxDepth(int methodDepth, Hashtable<String, MethodNode> symboltrees) {
        return 1 + e.maxDepth(methodDepth, symboltrees);
    }
    
//     public Tree.Exp translate() { return null; }
}