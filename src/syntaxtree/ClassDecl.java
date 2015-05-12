package syntaxtree;
import java.util.Hashtable;
import java.util.ArrayList;
import symboltree.*;

public class ClassDecl extends MiniJavaParserToken {
    private Identifier i;
    private VarDeclList l;
    private MethodDeclList m;
    
    private String superClass = "java/lang/Object";
    boolean extendsSomething = false;
    ArrayList<Node> classNodes = new ArrayList<Node>();
    ArrayList<String> superClassNames = new ArrayList<String>();
        
    public ClassDecl(Identifier id, VarDeclList li, MethodDeclList ma){
	    i=id;
	    l=li;
	    m=ma;
    }
//     
//     public boolean equals(ClassDecl othercd) {
//         return name().equals(othercd.name());
//     }

    public String toString() {
        return String.format("class %s {%s, %s}", i.toString(),l.toString(),m.toString());
    }
    
    public String strRepr(int level) {
        return String.format("%s<class name=%s>\n%s%s%s</class>\n",
                             tab(level),i,l.strRepr(level+1),
                             m.strRepr(level+1),tab(level));
    }
    
    public String name() {
        return i.toString();
    }
    
    public VarDeclList getVarDeclList() {
        return l;
    }

    public MethodDeclList getMethodDeclList() {
        return m;
    }
    
    public String getExtends(){
	    return superClass;
    }
    
    public void setExtends(String sc){
	    superClass = sc;
	    extendsSomething = true;
    }
    
    public boolean extendsSomething() {
        return extendsSomething;
    }

    public void addClassNodes(ArrayList<Node> classNode) {
        classNodes.addAll(classNode);
//         System.out.println(String.format("ClassDecl.addClassNodes: add %s => classNodes = %s",
//             classNode, classNodes));
    }

    public void addSuperClassNames(ArrayList<String> superClassName) {
        superClassNames.addAll(superClassName);
    }

    public ArrayList<Node> getClassNodes() {
        return classNodes;
    }

    public ArrayList<String> getSuperClassNames() {
        return superClassNames;
    }
    
    public String generateJVM(Hashtable<String, MethodNode> symboltrees) {
        String jvmCode = "";
        String constructor = String.format(".method public <init>()V\n" + 
                        	"aload_0\n" +
                        	"invokespecial %s/<init>()V\n" +
                        	"return\n" +
                        	".end method\n\n", getExtends());
        String printMethod = "";
        String printIntMethod = "";
        if (!extendsSomething) {
            printMethod = ".method public static print(Ljava/lang/String;)V\n" +    
                ".limit locals 5\n" +
                ".limit stack 5\n" +
                "aload 0\n" +
                "getstatic java/lang/System/out Ljava/io/PrintStream;\n" +
                "swap\n" +
                "invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V\n" +
                "return\n" +
                ".end method\n\n";    
            printIntMethod = ".method public static print(I)V\n" +    
                ".limit locals 5\n" +
                ".limit stack 5\n" +
                "iload 0\n" +
                "getstatic java/lang/System/out Ljava/io/PrintStream;\n" +
                "swap\n" +
                "invokevirtual java/io/PrintStream/print(I)V\n" +
                "return\n" +
                ".end method\n\n";
        }
        String methods = m.generateJVM(symboltrees);
        jvmCode += String.format(".class public %s\n.super %s\n%s%s%s%s%s",
            i, getExtends(), l.generateFieldJVM(), constructor, printIntMethod, printMethod, methods);
        return jvmCode;            
    }
}