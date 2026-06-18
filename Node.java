import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name;
    private List<Node> children;

    public Node(String name) {
        this.name = name;
        this.children = new ArrayList<Node>();
    }

    public void add(Node child) {
        this.children.add(child);
    }

    public String getName() {
        return name;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void print(String prefix) {
        System.out.println(prefix + name);

        for (Node child : children) {
            child.print(prefix + "  ");
        }
    }
}