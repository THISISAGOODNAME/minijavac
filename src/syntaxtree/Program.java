package syntaxtree;

public class Program extends MiniJavaParserToken {
    private ClassDeclList l;

    public Program(ClassDeclList li) {
    	l=li;
    }

    public String toString() {
        return "" + l;
    }
    
    public String strRepr(int level) {
        return String.format("<program>\n%s</program>", l.strRepr(level+1));
    }
    
    public ClassDeclList getClassDeclList() {
        return l;
    }    
}
