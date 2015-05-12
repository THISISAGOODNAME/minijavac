package syntaxtree;
import java.util.Vector;
import java.util.Hashtable;
import symboltree.*;

public class StatementList extends SyntaxTreeList {
    
    public StatementList() {
        super("statement_list");
    }

    public void addElement(Statement s){
	    list.addElement(s);
    }

    public Statement elementAt(int i){
	    return (Statement)list.elementAt(i);
    }

    public String toString() {
        String ret = "";
        if (list.size() > 1) ret = list.elementAt(0).toString();
        for (int i=1; i<list.size(); i++) ret += ";\n" + list.elementAt(i).toString();
        return ret;
    }
    
    public boolean typeCheck(Hashtable symbolTrees, String currentClass, String currentMethod) throws TypeException {
        for (int i = 0; i < size(); i++) {
            if (!elementAt(i).typeCheck(symbolTrees, currentClass, currentMethod)) return false;
        }
        return true;
    }
    
//     public Tree.SEQ translate() {
//         return (size()>0)? new Tree.SEQ(first().translate(),rest().translate()) : null;
//     }

    private Statement first() {
        return elementAt(0);
    }
    
    private StatementList rest() {
        StatementList newSL = new StatementList();
        for (int i = 1; i<size(); i++) {
            newSL.addElement(elementAt(i));
        }
        return newSL;
    }
    
    public String generateJVM(MethodNode method) {
        String jvmCode = "";
        for (int i=0; i<size(); i++) {
            jvmCode += elementAt(i).generateJVM(method);
        }
        return jvmCode;
    }
    
    public int maxDepth(int methodDepth, Hashtable<String, MethodNode> symboltrees) {
        int max = 0;
        for (int i=0; i<size(); i++) {
            int newMax = elementAt(i).maxDepth(methodDepth, symboltrees);
//             System.out.println(String.format("Depth of stm %s= %s", elementAt(i), newMax));
            if (newMax > max) max = newMax;
        }
        return max;
    }
}