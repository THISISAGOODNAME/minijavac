package syntaxtree;
// import temp.Label;
import symboltree.*;

public class Or extends BoolComp {

    public Or(Exp exp1, Exp exp2) {
    	super(exp1, exp2, "||");
    }
    
    public String generateFetchJVM(MethodNode method) {
        Label t = new Label();
        Label f = new Label();
        Label end = new Label();
        return String.format(
            "%sifgt %s\n%sifgt %s\ngoto %s\n%s:\niconst_1\ngoto %s\n%s:\niconst_0\n%s:\n",
            e1.generateFetchJVM(method), t, e2.generateFetchJVM(method), t,
            f, t, end, f, end);
    }
}