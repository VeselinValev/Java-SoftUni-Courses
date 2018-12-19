import java.util.ArrayList;
import java.util.List;

public class Tree {

    private Node root;

    public Tree(int value) {
        this.setRoot(value);
    }

    public Tree() {
    }

    public void addChild(Node node){
        this.root.addChild(node);
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void setRoot(int value) {
        this.root = new Node(value);
    }

    class Node {
        private int value;
        private List<Node> children;

        public Node(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        public int getValue() {
            return this.value;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void addChild(Node node) {
            this.children.add(node);
        }
    }
}




