package symboltree;
import java.util.ArrayList;
import syntaxtree.Type;
import syntaxtree.TypeException;

public class Node {
    String key;
    String value;
    Type type;
    int varNumb;

    Node left;
    Node right;
    
    int level;

    public Node(String k, String val, Type typ){
    	key=k;
    	value=val;
    	type=typ;
    }

    public Node(String k, Type typ){
    	key=k;
    	type=typ;
    }

    public Node(String k){
	    key=k;
    }
    
    public void setVarNumb(int i) {
        varNumb = i;
    }
    
    public int getVarNumb() {
        return varNumb;
    }
    
    public boolean addNode(Node n){
        if(this.getKey().compareTo(n.getKey()) == 0) {
            return false;
        } else {
            if(n.getKey().compareTo(this.getKey()) > 0) {
            	return addRight(n);
        	} else {
        	    return addLeft(n);
    	    }
        }
    }
    
    public static boolean contains(Node n, String key) {
        if (n == null) return false;
        else return (n.getNode(key) != null);
    }
    
    public Node getNode(String key){
    	if (key.compareTo(this.getKey()) != 0) {
    	    if (key.compareTo(this.getKey())>0){
        		if (this.getRight() == null){
        		    return null;
        		} else
        		    return this.getRight().getNode(key);
    	    } else {
        		if (this.getLeft() == null){
        		    return null;
        		}
        		else
        		    return this.getLeft().getNode(key);
    	    }
    	} else return this;
    }

    public void setLeft(Node l){
	    left=l;
    }

    public void setRight(Node r){
	    right=r;
    }

    public boolean addLeft(Node l){
	    if (left == null) {
            left = l;
            return true;
        }
        else {
	        boolean b = left.addNode(l);
	        return b;
        }
    }

    public boolean addRight(Node r){
        if (right == null) {
	        right = r;
	        return true;
        }
	    else {
	        boolean b = right.addNode(r);
	        return b;
        }
    }

    public String getKey(){
	return key;
    }

    public Type getType(){
	return type;
    }

    public String getValue(){
	return value;
    }

    public void setValue(String val){
	value=val;
    }

    public void setType(Type typ){
	type=typ;
    }

    public Node getLeft(){
	return left;
    }

    public Node getRight(){
	return right;
    }
    
    public void setLevel(int i){
	level=i;
    }

    public String toString(){
    	String desc = "";
    	for(int i=0;i<this.level;i++)
    	    desc+="\t";
    	desc+=this.getType() + " " + this.getKey() + "\n";
    	return desc;
    }

}