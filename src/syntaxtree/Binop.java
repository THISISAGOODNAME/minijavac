package syntaxtree;
import java.util.Hashtable;
import symboltree.*;

public abstract class Binop extends Exp {
    
    Exp e1;
    Exp e2;
    String op;
    Hashtable<String, String> opToDesc;
    Hashtable<String, Integer> opToInt;
    int treeBinop;
    public final static int PLUS=1, MINUS=2, MUL=3,
	DIV=4, AND=5, OR=6, LSHIFT=7, RSHIFT=8,
	ARSHIFT=9, XOR=10, LT=11, LE=12, GT=13, GE=14, EQ=15, NE=16;
    
    public Binop(Exp exp1, Exp exp2, String o){
    	e1=exp1;
    	e2=exp2;
    	op = o;
    	opToDesc = new Hashtable<String, String>();
        opToDesc.put("<", "lt");
        opToDesc.put("<=", "le");
        opToDesc.put(">", "gt");
        opToDesc.put(">=", "ge");
        opToDesc.put("==", "eq");
        opToDesc.put("!=", "ne");
        opToDesc.put("+", "add");
        opToDesc.put("-", "sub");
        opToDesc.put("*", "mul");
        opToDesc.put("&&", "and");
        opToDesc.put("||", "or");
        opToInt = new Hashtable<String, Integer>();
        opToInt.put("<", LT);
        opToInt.put("<=", LE);
        opToInt.put(">", GT);
        opToInt.put(">=", GE);
        opToInt.put("==", EQ);
        opToInt.put("!=", NE);
        opToInt.put("+", PLUS);
        opToInt.put("-", MINUS);
        opToInt.put("*", MUL);
        opToInt.put("&&", AND);
        opToInt.put("||", OR);
        treeBinop = opToInt.get(op);
    }
    
    public int getLine() {
        return e1.getLine();
    }
    
    public int getColumn() {
        return e1.getLine();
    }
    
    public String strRepr(int level) {
        return String.format(
            "%s<binop operator=%s >\n%s<left>\n%s%s</left>\n%s<right>\n%s%s</right>\n%s</binop>\n",
            tab(level), op, tab(level+1), e1.strRepr(level+2), tab(level+1),
            tab(level+1), e2.strRepr(level+2), tab(level+1), tab(level));
    }

    public String toString() {
        return String.format("%s%s%s", e1, op, e2);
    }
    
    public int maxDepth(int methodDepth, Hashtable<String, MethodNode> symboltrees) {
        return Math.max(e1.maxDepth(methodDepth, symboltrees), e2.maxDepth(methodDepth, symboltrees) + 1);
    }
    
    public String generateFetchJVM(MethodNode method) {
        return String.format("%s%s", e1.generateFetchJVM(method), e2.generateFetchJVM(method));
    }
    
    public String generateStoreJVM(MethodNode method) {
        return "";
    }
}