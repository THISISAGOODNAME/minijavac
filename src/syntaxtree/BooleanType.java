package syntaxtree;

public class BooleanType extends Type{
    
    public BooleanType(){}
    
    public BooleanType(int l, int c) {
    setPosition(l,c);
    }

    public String toString() {
        return "boolean";
    }
    
    public String strRepr(int level) {
        return "boolean";
    }

    public String jvmType() {
        return "I";
    }
}