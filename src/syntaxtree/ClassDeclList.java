package syntaxtree;
import java.util.Vector;

public class ClassDeclList extends SyntaxTreeList {
    
    public ClassDeclList() {
        super("class_list");
    }

    public void addElement(ClassDecl s){
	    list.addElement(s);
    }

    public ClassDecl elementAt(int i){
	    return (ClassDecl)list.elementAt(i);
    }
    
//     public String strRepr(int level) {
//         String ret = "";
//         if (size() > 1) {
//             ret += String.format("%s<%s>\n", tab(level), desc);
//             level++;
//         }
//         for (int i=0; i<size(); i++)
//             ret += elementAt(i).strRepr(level);
//         if (size() > 1)
//             ret += String.format("%s</%s>\n",tab(level-1), desc);
//         return ret;
//     }
}