package symboltree;
import java.util.ArrayList;
import syntaxtree.Type;
import syntaxtree.MethodDecl;
import syntaxtree.ClassDecl;

public class MethodNode extends Node {
    
    MethodDecl md;
    ClassDecl cd;
    ArrayList<Node> parameters = new ArrayList<Node>();
    ArrayList<Node> locals = new ArrayList<Node>();
    ArrayList<Node> classNodes = new ArrayList<Node>();
    String className;
    int level;

    public MethodNode(MethodDecl m, ClassDecl classdecl){
    	super(m.name(), m.type());
    	md = m;
    	cd = classdecl;
	    className=classdecl.name();
    }
    
    public MethodNode(ClassDecl classdecl) {
        super(classdecl.name());
        cd = classdecl;
    }
    
    public boolean addParameterNode(Node n) {
        parameters.add(n);
        return addRight(n);
    }
    
    public boolean addLocalNode(Node n){
    	if (addRight(n)) {
        	locals.add(n);
    	    return true;
	    } else {
    	    return false;
    	}    
    }
    
    public void setClassNodes(ArrayList<Node> n) {
        classNodes = n;
//         System.out.println(String.format("MethodNode.setClassNodes: classNodes.size() = %s, \n%s", classNodes.size(), classNodes));
    }

    public String getMethodDescription() {
        String desc = key + "(";
        if (parameters.size() > 0)
            desc += parameters.get(0).getType();
        for (int i = 1; i < parameters.size(); i++)
            desc += "," + parameters.get(0).getType();
        desc += ")";
        return desc;    
    }
    
    public String getClassName() {
	    if (className != null ) return className;
	    else return key;
    }
    
    public ClassDecl getClassDecl() {
	    return cd;
    }

    public void setLevel(int i){
	level=i;
    }

    public String toString(){
    	String desc = "";
    	Node temp;
    
    	if(md == null){
    	    this.setLevel(2);
    	    desc=String.format("\t\t"+"Class: %s\n", this.getClassName());
    	    desc+="\t\t\tFields:\n"; 
    	    for(int n=0;n<locals.size();n++){
        		temp=locals.get(n);
        		temp.setLevel(4);
        		desc+=temp;
    	    }
    	    return desc;
    	}
    
    	this.setLevel(4);
    	for(int i=0;i<level;i++)
    	    desc+="\t";
    	desc += String.format("Method: %s %s\n", this.md.type(), this.md.name());
    	for(int j=0;j<level+1;j++)
    	    desc+="\t";
    	desc+="Parameters:\n";
    
    	//Add parameters
    
    	for(int k=0;k<parameters.size();k++){
    	    temp=parameters.get(k);
    	    temp.setLevel(this.level+2);
    	    desc+=temp;
    	}
    
    	for(int l=0;l<level+1;l++)
    	    desc+="\t";
    	desc+="Locals:\n";
    
    	for(int m=0;m<locals.size();m++){
    	    temp=locals.get(m);
    	    temp.setLevel(this.level+2);
    	    desc+=temp;
    	}
    	
    	return desc;
    }

    public Node getMethodVariable(String key) {
        Node var = null;
        if (this.getRight() != null)
            var = this.getRight().getNode(key);
//         if (var == null && this.getLeft() != null)
//             var = this.getLeft().getNode(key);
        for (int i = 0; i < classNodes.size(); i++) {
            if (var == null) {
//                 System.out.println(String.format("MethodNode.getMethodVariable: i=%s, %s. (classNodes.size() = %s)",
//                     i, classNodes, classNodes.size()));
                Node classNode = classNodes.get(i);    
                if (classNode != null)    
                    var = classNode.getNode(key);
            }
            else
                break;
        }
        return var;
    }
    
    public Node getClassVariable(String key) {
        Node var = null;
        for (int i = 0; i < classNodes.size(); i++) {
            if (var == null) {
                Node classNode = classNodes.get(i); 
                if (classNode != null)    
                    var = classNode.getNode(key);
            } else
                break;
        }
        return var;
    }
    
    public ArrayList<Node> getParameters() {
        return parameters;
    }
    
    public ArrayList<Node> getLocals() {
	    return locals;
    }
    
    public ArrayList<Node> getClassNodes() {
	    return classNodes;
    }

    public MethodDecl getMethodDecl() {
        return md;
    }
    
}
