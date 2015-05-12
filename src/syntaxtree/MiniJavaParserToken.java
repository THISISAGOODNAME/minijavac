package syntaxtree;

public abstract class MiniJavaParserToken {
    private int line;
    private int column;
    
    public void setPosition(int l, int c) {
        line = l;
        column = c;
    }
    
//     public String toString(int level) {
//         return strRepr(level);
//     }
    
//     {
//         return String.format("\n%s%s",tab(level),strRepr(level));
//     }
    
    public abstract String strRepr(int level);
    
    public String paramStrRepr(int level) { return strRepr(level); }
    
    public String tab(int level) {
        return String.format("%"+level+"s","");
    }
    
    public int getLine() {
        return line;
    }
    
    public int getColumn() {
        return column;
    }
       
    public String getPosition() {
        return String.format("(line %d, col %d)", getLine(), getColumn());
    }
}
    