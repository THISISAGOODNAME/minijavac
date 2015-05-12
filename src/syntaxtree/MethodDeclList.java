package syntaxtree;
import java.util.Vector;
import java.util.Hashtable;
import symboltree.*;

public class MethodDeclList extends SyntaxTreeList {
    
    public MethodDeclList() {
        super("method_list");
    }

    public void addElement(MethodDecl s){
	    list.addElement(s);
    }

    public MethodDecl elementAt(int i){
	    return (MethodDecl)list.elementAt(i);
    }
    
    public String generateJVM(Hashtable<String, MethodNode> symboltrees) {
        String jvmCode = "";
        for (int i = 0; i < size(); i++) {
            MethodDecl md = elementAt(i);
            jvmCode += md.generateJVM(symboltrees) + "\n";
        }
        return jvmCode;            
    }
    
    public MethodDecl getMethodDecl(String methodName) {
        for (int i = 0; i < size(); i++) {
            MethodDecl md = elementAt(i);
            if (md.name().equals(methodName)) return md;
        }
        return null;
    }

}