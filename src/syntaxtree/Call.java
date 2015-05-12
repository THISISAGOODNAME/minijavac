package syntaxtree;
import java.util.Hashtable;
import java.util.ArrayList;
import symboltree.*;
// import Tree.*;
// import temp.Label;

public class Call extends Exp{

    private Exp e;
    private Identifier i;
    private ExpList el;
    private Type objectType;
    Type returnType;
    String typesString;
    String objectClassName;
    
    public Call(Exp exp, Identifier id, ExpList l){
    	e=exp;
    	i=id;
    	el=l;
    	objectType = null;
    }

    public String toString() {
        return String.format("%s.%s(%s)", e, i, el);
    }
    
    public String strRepr(int level) {
        return String.format("%s<call name=%s>\n%s<caller>\n%s%s</caller>\n%s%s</call>\n",
            tab(level), i, tab(level+1), e.strRepr(level+2), tab(level+1),
            el.paramStrRepr(level+1), tab(level));
    }

    public Type getType(Hashtable symbolTrees, String currentClass, String currentMethod)
        throws TypeException {
        objectType = e.getType(symbolTrees, currentClass, currentMethod);
        typesString = el.getTypesString(symbolTrees, currentClass, currentMethod);
        if (!(objectType instanceof IdentifierType)) {
            throw new TypeException(String.format(
                "Object of type %s does not contain method %s(%s). %s",
                objectType, i, typesString, i.getPosition()));
        }
//         MethodNode methodRoot = (MethodNode)symbolTrees.get(className + "." + i);
        String className = objectType.toString();
        MethodNode classRoot = (MethodNode)symbolTrees.get(className);
        ClassDecl cd = classRoot.getClassDecl();
        ArrayList<String> superClassNames = cd.getSuperClassNames();
        MethodNode methodRoot = null;
        for (int j=0; j<superClassNames.size(); j++) {
            if (methodRoot == null) {
                methodRoot = (MethodNode)symbolTrees.get(superClassNames.get(j) + "." + i);
                if (methodRoot != null) objectClassName = superClassNames.get(j);
            }
        }
        if (methodRoot == null)
            throw new TypeException(String.format(
                "Class %s does not contain method %s(%s). %s",
                objectType, i, typesString, i.getPosition()));
        ArrayList<Node> parameters = methodRoot.getParameters();
        if (parameters.size() != el.size()) {
            throw new TypeException(String.format(
                "Method %s cannot take parameters (%s). %s",
                methodRoot.getMethodDescription(),
                typesString,
                el.getPosition()));
        } else {
            for (int i = 0; i < el.size(); i++) {
                Exp paramExp = el.elementAt(i);
                Type paramExpType = paramExp.getType(symbolTrees, currentClass, currentMethod);
                Node param = parameters.get(i);
                if (!paramExpType.equals(param.getType())) //Tweaka felmeddelande
                    throw new TypeException(String.format("Found %s, required %s. %s",
                         paramExpType, param.getType(), el.getPosition()));
            }
        }
        returnType = methodRoot.getType();
        return returnType;
    }
    
//     public Tree.Exp translate() {
//         String fun_name = String.format("%s.%s(%s)", objectType, i, typesString);
//         Label fun_lab = new Label(fun_name);
//         Tree.NAME function = new Tree.NAME(fun_lab);
//         Tree.ExpList parameters = new Tree.ExpList(e.translate(), el.translate());
//         return new Tree.CALL(function, parameters);
//     }
    
    public String generateFetchJVM(MethodNode method) {
        String getObject = e.generateFetchJVM(method);
        String getParams = el.generateFetchJVM(method);
        String jvmObjType = objectType.toString();
        String jvmParamTypes = el.jvmTypes();
        String jvmRetType = returnType.jvmType();
        String jvmCode = String.format("%s%sinvokevirtual %s/%s(%s)%s\n",
            getObject, getParams, jvmObjType, i, jvmParamTypes, jvmRetType);
        return jvmCode;    
    }
    
    public String generateStoreJVM(MethodNode method) {
        return "";
    }
    
    public int maxDepth(int methodDepth, Hashtable<String,MethodNode> symboltrees) {
        MethodNode methodRoot = symboltrees.get(objectClassName + "." + i);
        MethodDecl md = methodRoot.getMethodDecl();
        int parameterStackSize  = 1 + el.maxDepth(methodDepth, symboltrees);
//         System.out.println(String.format("MaxDepth = %s for parameters in %s\nParameters: %s",
//             parameterStackSize, i, typesString));
        return Math.max(md.maxDepth(methodDepth + 1, symboltrees),
                Math.max(parameterStackSize, e.maxDepth(methodDepth, symboltrees)));
    }
}