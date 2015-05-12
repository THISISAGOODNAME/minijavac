package syntaxtree;
import java.util.Hashtable;

public class Identifier extends MiniJavaParserToken {

    private String s;
    private static Hashtable<String, Integer> reserved_words;
    
    public Identifier(String st){
	    if (reserved_words == null) setReservedWords();
	    if (reserved_words.containsKey(st)) {
		    s = "_" + st;
	    } else {
		    s=st;
	    }
    }
    
    public String toString(){
	    return s;
    }

    public String strRepr(int level) {
        return toString();
    }
    
    public void typeCheck(Hashtable symbolTrees) throws TypeException {
	    if (!symbolTrees.containsKey(s)) throw new TypeException(String.format(
	    	"Unidentified type %s. %s", s, getPosition()));
    }
    
    public void setReservedWords() {
	    reserved_words=new Hashtable<String, Integer>();
	
		reserved_words.put("stack", 1);
		reserved_words.put(".catch", 1);
		reserved_words.put(".class",1);
		reserved_words.put(".end",1);
		reserved_words.put(".field", 1);
		reserved_words.put(".implements", 1);
		reserved_words.put(".interface", 1);
		reserved_words.put(".limit", 1);
		reserved_words.put(".line", 1);
		reserved_words.put(".method", 1);
		reserved_words.put(".set", 1);
		reserved_words.put(".source", 1);
		reserved_words.put(".super", 1);
		reserved_words.put(".no_super", 1);
		reserved_words.put(".throws", 1);
		reserved_words.put(".var", 1);
		reserved_words.put(".class_attribute", 1);
		reserved_words.put(".field_attribute", 1);
		reserved_words.put(".method_attribute", 1);
		reserved_words.put(".code_attribute", 1);
		reserved_words.put(".inner_class_attr", 1);
		reserved_words.put(".inner_class_spec_attr", 1);
		reserved_words.put(".synthetic", 1);
		reserved_words.put(".enclosing_method_attr", 1);
		reserved_words.put(".deprecated", 1);
		reserved_words.put(".signature_attr", 1);
		reserved_words.put(".runtime_visible_annotation", 1);
		reserved_words.put(".runtime_invisible_annotation", 1);
		reserved_words.put(".runtime_param_visible_annotation", 1);
		reserved_words.put(".runtime_param_invisible_annotation", 1);
		reserved_words.put(".annotation_attr", 1);
		reserved_words.put(".param", 1);
		reserved_words.put(".annotation", 1);
		reserved_words.put(".int_kind", 1);
		reserved_words.put(".byte_kind", 1);
		reserved_words.put(".char_kind", 1);
		reserved_words.put(".short_kind", 1);
		reserved_words.put(".bool_kind", 1);
		reserved_words.put(".str_kind", 1);
		reserved_words.put(".long_kind", 1);
		reserved_words.put(".doub_kind", 1);
		reserved_words.put(".float_kind", 1);
		reserved_words.put(".enum_kind", 1);
		reserved_words.put(".ann_kind", 1);
		reserved_words.put(".arr_kind", 1);
		reserved_words.put(".cls_kind", 1);
		reserved_words.put(".arr_elem", 1);
		reserved_words.put(".annot_elem", 1);
		reserved_words.put(".elem", 1);
		reserved_words.put(".annotation_default", 1);
		reserved_words.put("from", 1);
		reserved_words.put("method", 1);
		reserved_words.put("to", 1);
		reserved_words.put("is", 1);
		reserved_words.put("using", 1);
		reserved_words.put("tableswitch", 1);
		reserved_words.put("lookupswitch", 1);
		reserved_words.put("default", 1);
		reserved_words.put("public", 1);
		reserved_words.put("private", 1);
		reserved_words.put("protected", 1);
		reserved_words.put("static", 1);
		reserved_words.put("final", 1);
		reserved_words.put("synchronized", 1);
		reserved_words.put("volatile", 1);
		reserved_words.put("transient", 1);
		reserved_words.put("native", 1);
		reserved_words.put("interface", 1);
		reserved_words.put("abstract", 1);
		reserved_words.put("strictfp", 1);
		reserved_words.put("annotation", 1);
		reserved_words.put("enum", 1);
	}
}