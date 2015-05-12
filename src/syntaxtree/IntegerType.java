package syntaxtree;

public class IntegerType extends Type {
    
    public IntegerType(int l, int c) {
        setPosition(l,c);
    }
    
    public IntegerType() {
    }

    public String toString() {
        return "int";
    }
    
    public String strRepr(int level) {
        return "" + this;
    }
    
    public String jvmType() {
        return "I";
    }
}