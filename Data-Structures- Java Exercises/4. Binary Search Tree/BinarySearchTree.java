import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;
    private int nodesCount;

    public BinarySearchTree() {
    }

    private BinarySearchTree(Node root) {
        this.preOrderCopy(root);
    }

    private void preOrderCopy(Node node) {
        if (node == null) {
            return;
        }

        this.insert(node.value);
        this.preOrderCopy(node.left);
        this.preOrderCopy(node.right);
    }

    public Node getRoot() {
        return this.root;
    }

    public int getNodesCount() {
        return this.nodesCount;
    }

    public void insert(T value) {
        this.nodesCount++;
        if (this.root == null) {
            this.root = new Node(value);
            return;
        }
        Node parent = null;
        Node current = this.root;
        while (current != null) {
            parent = current;
            parent.childrenCount++;

            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                return;
            }
        }
        Node newNode = new Node(value);
        if (value.compareTo(parent.value) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    public boolean contains(T value) {
        Node current = this.root;
        while (current != null) {
            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return current != null;
    }

    public BinarySearchTree<T> search(T item) {
        Node current = this.root;
        while (current != null) {
            if (item.compareTo(current.value) < 0) {
                current = current.left;
            } else if (item.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return new BinarySearchTree<>(current);
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }
        this.eachInOrder(node.left, consumer);
        consumer.accept(node.value);
        this.eachInOrder(node.right, consumer);
    }

    public Iterable<T> range(T from, T to) {
        Deque<T> queue = new LinkedList<>();
        this.range(this.root, queue, from, to);
        return queue;
    }

    private void range(Node node, Deque<T> queue, T startRange, T endRange) {
        if (node == null) {
            return;
        }
        int compareStart = startRange.compareTo(node.value);
        int compareEnd = endRange.compareTo(node.value);
        if (compareStart < 0) {
            this.range(node.left, queue, startRange, endRange);
        }
        if (compareStart <= 0 && compareEnd >= 0) {
            queue.addLast(node.value);
        }
        if (compareEnd > 0) {
            this.range(node.right, queue, startRange, endRange);
        }
    }

    private T minValue(Node root) {
        T minv = root.value;
        while (root.left != null) {
            minv = root.left.value;
            root = root.left;
        }
        return minv;
    }

    public void deleteMin() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }
        Node min = this.root;
        Node parent = null;
        while (min.left != null) {
            parent = min;
            parent.childrenCount--;
            min = min.left;
        }
        if (parent == null) {
            this.root = this.root.right;
        } else {
            parent.left = min.right;
        }
        this.nodesCount--;
    }

    public void deleteMax() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }
        Node parent = null;
        Node max = this.root;
        while (max.getRight() != null) {
            parent = max;
            max = max.right;
        }
        if (parent == null) {
            this.root = this.root.left;
        } else {
            parent.right = max.left;
        }
        this.nodesCount--;
    }

    public T ceil(T element) {
        Node currentNode = this.root;
        T result = null;
        while (currentNode != null) {
            if (element.compareTo(currentNode.value) < 0) {
                result = currentNode.value;
                currentNode = currentNode.left;
            } else if (element.compareTo(currentNode.value) > 0) {
                currentNode = currentNode.right;
            } else {
                result = currentNode.value;
                break;
            }
        }
        return result;
    }

    public T floor(T element) {
        Node currentNode = this.root;
        T result = null;
        while (currentNode != null) {
            if (element.compareTo(currentNode.value) < 0) {
                currentNode = currentNode.left;
            } else if (element.compareTo(currentNode.value) > 0) {
                result = currentNode.value;
                currentNode = currentNode.right;
            } else {
                result = currentNode.value;
                break;
            }
        }
        return result;
    }

    public void delete(T key) {
        Map<String, Node> nodes = this.findNodeAndParent(key);
        if (nodes.get("toDelete") == null) {
            return;
        }
        Node nodeToDelete = nodes.get("toDelete");
        if (nodes.get("parent") == null) {
            Node currentNode = this.root.right;
            Node parent = this.root;
            Node grandParent = null;
            Node left = this.root.left;
            Node right = this.root.right;
            while (currentNode != null) {
                grandParent = parent;
                parent = currentNode;
                currentNode = currentNode.left;
            }
            this.root = parent;
            this.root.left = left;
            this.root.right = right;
            grandParent.left = null;
            return;
        }
        Node parent = nodes.get("parent");
        if (nodeToDelete.left == null && nodeToDelete.right == null) {
            if (parent.value.compareTo(nodeToDelete.value) > 0) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (nodeToDelete.left == null) {
            parent.right = nodeToDelete.right;
        } else if (nodeToDelete.right == null) {
            parent.left = nodeToDelete.left;
        } else {
            this.deleteNode(nodeToDelete, parent);
        }
    }

    private void deleteNode(Node nodeToDelete, Node parent) {
        Node nodeToMove = nodeToDelete.left;
        Node actualNode;
        if (nodeToDelete.value.compareTo(parent.value) > 0) {
            parent.right = nodeToDelete.right;
            actualNode = parent.right;
        } else {
            parent.left = nodeToDelete.right;
            actualNode = parent.left;
        }
        Node currentNode = actualNode.left;
        Node currentParent;
        if (currentNode == null) {
            actualNode.left = nodeToMove;
        }
        while (currentNode != null) {
            currentNode = currentNode.left;
            currentParent = currentNode;
            if (currentNode == null) {
                currentParent.left = nodeToMove;
            }
        }
    }

    private Map<String, Node> findNodeAndParent(T value) {
        Node currentNode = this.root;
        Map<String, Node> result = new HashMap<>();
        Node parent = null;
        while (currentNode != null) {
            if (currentNode.value.compareTo(value) > 0) {
                parent = currentNode;
                currentNode = currentNode.left;
            } else if (currentNode.value.compareTo(value) < 0) {
                parent = currentNode;
                currentNode = currentNode.right;
            } else {
                result.put("parent", parent);
                result.put("toDelete", currentNode);
                break;
            }
        }
        return result;
    }

    public int rank(T item) {
        int counter = 0;
        if (this.root == null) {
            return 0;
        }
        ArrayDeque<Node> nodeStack = new ArrayDeque<>();
        nodeStack.push(this.root);
        while (!nodeStack.isEmpty()) {
            Node currentNode = nodeStack.pollLast();
            if (item.compareTo(currentNode.value) > 0) {
                counter++;
            }
            if (currentNode.right != null) {
                nodeStack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                nodeStack.push(currentNode.left);
            }
        }
        return counter;
    }

    public T select(int n) {
        T result = null;
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }
        ArrayDeque<Node> nodeStack = new ArrayDeque<>();
        nodeStack.push(this.root);
        while (!nodeStack.isEmpty()) {
            Node currentNode = nodeStack.pollLast();
            if (n == this.rank(currentNode.value)) {
                result = currentNode.value;
            }
            if (currentNode.right != null) {
                nodeStack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                nodeStack.push(currentNode.left);
            }
        }
        return result;
    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        private int childrenCount;

        public Node(T value) {
            this.value = value;
            this.childrenCount = 1;
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return this.left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return this.value + "";
        }
    }
}

