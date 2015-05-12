package syntaxtree;
import java.util.Vector;

public abstract class SyntaxTreeList extends MiniJavaParserToken {
    Vector<MiniJavaParserToken> list;
    String desc;
    
    public SyntaxTreeList(String d) {
	    list = new Vector<MiniJavaParserToken>();
        desc = d;
    }

    public int size() {
	    return list.size();
    }
    
    public String strRepr(int level) {
        String ret = String.format("%s<%s size=%d>\n", tab(level), desc, size());
        for (int i=0; i<size(); i++)
            ret += list.elementAt(i).strRepr(level+1);
        ret += String.format("%s</%s>\n",tab(level), desc);
        return ret;
    }
    
    public String paramStrRepr(int level) {
        String ret = String.format("%s<parameters>\n", tab(level));
        for (int i=0; i<list.size(); i++)
            ret += list.elementAt(i).paramStrRepr(level+1);
        ret += String.format("%s</parameters>\n", tab(level));
        return ret;
    }
    
    public String toString() {
        String ret = "";
        if (list.size() > 1) ret = list.elementAt(0).toString();
        for (int i=1; i<list.size(); i++) ret += "," + list.elementAt(i).toString();
        return ret;
    }
}