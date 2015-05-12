package syntaxtree;
import java.util.Hashtable;
import symboltree.*;
// import Tree.*;

public class Assign extends Statement{

    private IdentifierExp i;
    private Exp e;

    public Assign(){}
    
    public Assign(IdentifierExp id, Exp exp){
    	i=id;
    	e=exp;
    }

    public String toString() {
        return "(" + i.toString() + " = " + e.toString() + ")";
    }
    
    public String strRepr(int level) {
        return String.format("%s<assign name=%s>\n%s%s</assign>\n",
            tab(level), i, e.strRepr(level+1), tab(level));
    }
    
    public boolean typeCheck(Hashtable symbolTrees, String currentClass, String currentMethod) throws TypeException {
        Type iType = i.getType(symbolTrees, currentClass, currentMethod);
        Type eType = e.getType(symbolTrees, currentClass, currentMethod);
        if (!eType.equals(iType, symbolTrees)) {
            throw new TypeException(String.format(
                "Found %s, required %s. %s", eType, iType, e.getPosition()));
        }
        return true;
    }

//     public Stm translate() {
//         Tree.Exp variable = i.translateToTemp();
//         Tree.Exp value = e.translate();
//         return new MOVE(variable, value);
//     }
    
    public String generateJVM(MethodNode method) {
        String jvmCode = String.format("%s%s",
            e.generateFetchJVM(method), i.generateStoreJVM(method));    
        return jvmCode;        
    }
    
    public int maxDepth(int methodDepth, Hashtable<String,MethodNode> symboltrees) {
        return Math.max(e.maxDepth(methodDepth, symboltrees),
                        i.maxDepth(methodDepth, symboltrees));
    }
}