package syntaxtree;
import symboltree.*;
// import temp.Label;

public class And extends BoolComp {

    public And(Exp exp1, Exp exp2) {
    	super(exp1, exp2, "&&");
    }
    
    public String generateFetchJVM(MethodNode method) {
        Label f = new Label();
        Label end = new Label();
        return String.format("%sifeq %s\n%sifeq %s\niconst_1\ngoto %s\n%s:\niconst_0\n%s:\n",
            e1.generateFetchJVM(method), f, e2.generateFetchJVM(method), f,
            end, f, end);
    }
}