package syntaxtree;

public class LongType extends Type {
    
    public LongType(int l, int c) {
        setPosition(l,c);
    }

    public String toString() {
        return "long";
    }
    
    public String strRepr(int level) {
        return "" + this;
    }
    
    public String jvmType() {
        return "I";
    }
}