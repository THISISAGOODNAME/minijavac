package syntaxtree;
import java.util.Hashtable;
import java.util.ArrayList;
import symboltree.*;

public class IdentifierType extends Type{

    private String st;
    ArrayList<String> superClassNames;

    public IdentifierType(String s){
	st=s;
    }
    
    public IdentifierType(String s, int l, int c){
	st=s;
    setPosition(l,c);
    }

    public String toString(){
	return st;
    }

    public String strRepr(int level) {
        return toString();
    }
    
    public String jvmType() {
        return (st.equals("void"))?"V":("L" + toString() + ";");
    }
    
    public String paramJvmType() {
        return "L" + (st.equals("void")?"":toString() + ";");
    }
    
    public String declaration(int i) {
        return String.format("new %s\nastore %d\n", st, i);
    }
    
    public boolean equals(Type t, Hashtable symboltrees) {
        if (superClassNames == null) {
            MethodNode my_class = (MethodNode)symboltrees.get(st);
            ClassDecl cd = my_class.getClassDecl();
            superClassNames = cd.getSuperClassNames();
        }
        for (int i=0; i<superClassNames.size(); i++) {
            if (superClassNames.get(i).equals(t.toString())) return true;
        }
        System.out.println(String.format("Type %s not among %s", t, superClassNames));
        return false;
    }
    
    public void typeCheck(Hashtable symbolTrees) throws TypeException {
	    if (!symbolTrees.containsKey(st)) throw new TypeException(String.format(
	    	"Unidentified type %s. %s", st, getPosition()));
    }
}