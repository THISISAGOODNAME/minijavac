package syntaxtree;
import java.util.Vector;
import java.util.Hashtable;
import java.util.ArrayList;
import symboltree.*;
// import irtree.Tree.*;

public class ExpList extends SyntaxTreeList {
    ArrayList<Type> types = new ArrayList<Type>();

    public ExpList(){
        super("parameters");
    }
    
    public void addElement(Exp s){
	    list.addElement(s);
    }

    public Exp elementAt(int i){
	    return (Exp)list.elementAt(i);
    }
    
    public String getTypesString(Hashtable symbolTrees, String currentClass, String currentMethod) throws TypeException {
        if (types.size() == 0) {
            for (int i=0; i<size(); i++) {
                types.add(elementAt(i).getType(symbolTrees, currentClass, currentMethod));
            }
        }
        String typeDesc = "";
        if (size() > 0) {
            typeDesc += types.get(0);
            for (int i=1; i<size(); i++)
                typeDesc += "," + types.get(i);
        }
        return typeDesc;
    }
    
    public String jvmTypes() {
        String desc = "";
        for (int i = 0; i < types.size(); i++)
            desc += types.get(i).paramJvmType();
        return desc;
    }
        
//     public Tree.ExpList translate() {
//         return (size()>0)? new Tree.ExpList(first().translate(),rest().translate()) : null;
//     }

    private Exp first() {
        return (size() > 0)? elementAt(0):null;
    }
    
    private ExpList rest() {
        ExpList newEL = new ExpList();
        for (int i = 1; i<size(); i++) {
            newEL.addElement(elementAt(i));
        }
        return newEL;
    }
    
    public String generateFetchJVM(MethodNode method) {
        String jvmCode = "";
        for (int i=0; i<size(); i++) {
            jvmCode += elementAt(i).generateFetchJVM(method);
        }
        return jvmCode;
    }
    
    public String generateFetchJVMBackwards(MethodNode method) {
        String jvmCode = "";
        for (int i=size()-1; i>=0; i--) {
            jvmCode += elementAt(i).generateFetchJVM(method);
        }
        return jvmCode;
    }
    
    public int maxDepth(int methodDepth, Hashtable<String,MethodNode> symboltrees) {
        int stackSize = 0;
        for (int i=0; i<size(); i++) {
            Exp e = elementAt(i);
            stackSize += e.maxDepth(methodDepth, symboltrees);
//             System.out.println(String.format("Depth of exp %s= %s (in %s)",
//                 e, e.maxDepth(methodDepth, symboltrees), types));
        }
        return stackSize;
    }
}