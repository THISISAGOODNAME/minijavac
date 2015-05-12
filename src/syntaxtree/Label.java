package syntaxtree;

public class Label {
    private String name;
    static int numberOfLabels = 0;
    
    public Label() {
        name = "Label" + ++numberOfLabels;
    }
    
    public String toString() {
        return name;
    }
}