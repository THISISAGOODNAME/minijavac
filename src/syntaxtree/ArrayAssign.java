package syntaxtree;
import java.util.Hashtable;
import java.lang.Math;
import symboltree.*;
// import Tree.*;

public class ArrayAssign extends Statement{

    private IdentifierExp ie;
    private Exp e1;
    private Exp e2;

    public ArrayAssign(){}
    
    public ArrayAssign(IdentifierExp id, Exp exp1, Exp exp2){
	ie=id;
	e1=exp1;
	e2=exp2;
    }
    
    public String toString() {
        return "(" + ie + ")[" + e1 + "] = " + e2;
    }
    
    public String strRepr(int level) {
        return String.format(
            "%s<array_assign name=%s>\n%s<index>\n%s%s</index>\n%s<exp>\n%s%s</exp>\n%s</array_assign>\n",
            tab(level), ie,
            tab(level+1), e1.strRepr(level+2), tab(level+1),
            tab(level+1), e2.strRepr(level+2), tab(level+1),
            tab(level));
    }
    
    public boolean typeCheck(Hashtable symbolTrees, String currentClass, String currentMethod) throws TypeException{
        IntegerType it = new IntegerType();
        if (!ie.getType(symbolTrees, currentClass, currentMethod).equals(new IntArrayType())) {
            throw new TypeException("Variable of integer array type required. " + ie.getPosition());
        }
        if (!e1.getType(symbolTrees, currentClass, currentMethod).equals(it)) {
            throw new TypeException("Index must be of integer type. " + e1.getPosition());
        }
        if (!e2.getType(symbolTrees, currentClass, currentMethod).equals(it)) {
            throw new TypeException("Variable of integer type required. " + e2.getPosition());
        }
        return true;
    }

//     public Tree.Stm translate() {
//         // Get address of array (MEM for store)
//         MEM baseAddr = new MEM(ie.translate());
//         // Get the (integer) value of the index expression (MEM for fetch)
//         MEM index = new MEM(e1.translate());
//         BINOP offset = new BINOP(BINOP.MUL, index, new CONST(4));
//         // Get address of element in array using the array address and the offset
//         // (MEM for store)
//         MEM memory = new MEM(new BINOP(BINOP.PLUS, baseAddr, offset));
//         Tree.Exp value = e2.translate();
//         // Place the value of expression "e" in memory at address "memory"
//         return new MOVE(memory, value);
//     }
    
    public String generateJVM(MethodNode method) {
        String jvmCode = String.format("%s%s%siastore\n", ie.generateFetchJVM(method),
            e1.generateFetchJVM(method), e2.generateFetchJVM(method));
        return jvmCode;
    }
    
    public int maxDepth(int methodDepth, Hashtable<String,MethodNode> symboltrees) {
        return Math.max(ie.maxDepth(methodDepth, symboltrees),
                        Math.max(e1.maxDepth(methodDepth, symboltrees) + 1,
                                 e2.maxDepth(methodDepth, symboltrees) + 2));
    }
}