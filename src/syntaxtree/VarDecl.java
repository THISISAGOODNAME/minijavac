package syntaxtree;
import java.util.Hashtable;

public class VarDecl extends MiniJavaParserToken {
    private Type t;
    private Identifier i;

    public VarDecl(Type ty,Identifier id){
	t=ty;
	i=id;
    }

    public String toString() {
        return String.format("%s %s", t, i);
    }
    
    public String strRepr(int level) {
        return String.format("%s<var_decl type=%s name=%s/>\n", tab(level), t, i);
    }
    
    public String paramStrRepr(int level) {
        return String.format("%s<parameter type=%s name=%s/>\n", tab(level), t, i);
    }
    
    public String name() {
        return i.toString();
    }
    
    public Type type() {
        return t;
    }

    public String generateJVM(int varNumb) {
        return t.declaration(varNumb);
    }
    
    public boolean equals(VarDecl othervd) {
        return type().equals(othervd.type());
    }
    
    public boolean typeCheck(Hashtable symbolTrees, String currentClass, String currentMethod)
    throws TypeException {
	    i.typeCheck(symbolTrees);
	    return true;	
    }
}