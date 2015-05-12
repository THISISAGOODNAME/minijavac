package syntaxtree;

public class IntArrayType extends Type{
    
    public IntArrayType(){}
    
    public IntArrayType(int l, int c) {
    setPosition(l,c);
    }
    
    public String toString() {
        return "int[]";
    }
    
    public String strRepr(int level) {
        return toString();
    }
    
    public String jvmType() {
        return "[I";
    }
}