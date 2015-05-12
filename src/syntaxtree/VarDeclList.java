package syntaxtree;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Hashtable;

public class VarDeclList extends SyntaxTreeList {
    
    public VarDeclList() {
        super("variable_declaration_list");
    }
    
    public void addElement(VarDecl s){
	    list.addElement(s);
    }

    public VarDecl elementAt(int i){
	    return (VarDecl)list.elementAt(i);
    }
    
    public String getJVMTypeString() {
        String types = "";
        for (int i=0; i<size(); i++) {
            VarDecl vd = elementAt(i); 
            types += vd.type().jvmType();
        }
        return types;
    }
    
    public String getTypesString() {
        String types = "";
        if (size() > 0) types += elementAt(0);
        for (int i=1; i<size(); i++) {
            VarDecl vd = elementAt(i); 
            types += "," + vd.type().jvmType();
        }
        return types;
    }
    
    public String generateJVM(int firstNumb) {
        String jvmCode = "";
        for (int i=0; i<size(); i++) {
            VarDecl vd = elementAt(i);            
            jvmCode += vd.generateJVM(firstNumb + i);
        }
        return jvmCode;
    }
    
    public String generateFieldJVM() {
        String jvmCode = "";
        for (int i=0; i<size(); i++) {
            VarDecl vd = elementAt(i);
            jvmCode += String.format(".field public %s %s\n",
                                     vd.name(), vd.type().jvmType());
        }
        return jvmCode;
    }
    
    public boolean typeCheck(Hashtable symbolTrees, String currentClass, String currentMethod) throws TypeException {
        for (int i = 0; i < size(); i++) {
            if (!elementAt(i).typeCheck(symbolTrees, currentClass, currentMethod)) return false;
        }
        return true;
    }

}