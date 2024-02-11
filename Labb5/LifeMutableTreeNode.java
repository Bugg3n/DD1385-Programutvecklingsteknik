import javax.swing.tree.DefaultMutableTreeNode;

public class LifeMutableTreeNode extends DefaultMutableTreeNode {
    private String name;
    private String text;
    private String type;

    public LifeMutableTreeNode(String name, String text, String type) {
        super(name);
        this.name = name;
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
