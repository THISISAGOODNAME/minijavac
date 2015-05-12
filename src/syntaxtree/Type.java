package syntaxtree;
import java.util.Hashtable;

public abstract class Type extends MiniJavaParserToken {

    public Type(){}
    
    public boolean equals(Type t, Hashtable symboltrees) {
        return this.toString().equals(t.toString());
    }
    
    public boolean equals(Type t) {
        return this.toString().equals(t.toString());
    }
    
    public abstract String jvmType();
    
    public String declaration(int i) {
        return String.format("ldc 0\nistore %d\n", i);
    }
    
    public String paramJvmType() { return jvmType(); }
    
    public void typeCheck(Hashtable symbolTrees) throws TypeException {}
}
