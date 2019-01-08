package firstLastList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AVL<T extends Comparable<T>> {

    private Node<T> root;

    public Node<T> getRoot() {
        return this.root;
    }

    public boolean contains(T item) {
        Node<T> node = this.search(this.root, item);
        return node != null;
    }

    public void insert(T item) {
        this.root = this.insert(this.root, item, null);
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node<T> node, Consumer<T> action) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, action);
        action.accept(node.value);
        this.eachInOrder(node.right, action);
    }

    private Node<T> insert(Node<T> node, T value, Node<T> parent) {
        if (node == null) {
            node = new Node<T>(value);
        } else if (value.compareTo(node.value) < 0) {
            node = this.insert(node.left, value,  node);
        } else if (value.compareTo(node.value) > 0) {
            node = this.insert(node.right, value,  node);
        }
        if (parent == null) {
            return node;
        }
        if (parent.value.compareTo(node.value) < 0){
            parent.right = node;
        }else if (parent.value.compareTo(node.value) > 0){
            parent.left = node;
        }

        this.updateHeight(parent);
        parent = this.balance(parent);

        return parent;
    }

    private Node<T> search(Node<T> node, T item) {
        if (node == null) {
            return null;
        }

        int cmp = item.compareTo(node.value);
        if (cmp < 0) {
            return search(node.left, item);
        } else if (cmp > 0) {
            return search(node.right, item);
        }

        return node;
    }

    private int height(Node<T> node){
        if (node == null){
            return 0;
        }

        return node.height;
    }

    private void updateHeight(Node<T> node){
        if (node != null){
            node.height = Math.max(this.height(node.left), this.height(node.right)) + 1;
        }
    }

    private Node<T> leftRotation(Node<T> node) {
        Node<T> tempNode = node.right;
        node.right = tempNode.left;
        tempNode.left = node;

        this.updateHeight(tempNode.left);
        this.updateHeight(tempNode.right);
        this.updateHeight(tempNode);

        return tempNode;
    }

    private Node<T> rightRotation(Node<T> node) {

        Node<T> tempNode = node.left;
        node.left = tempNode.right;
        tempNode.right = node;

        this.updateHeight(tempNode.left);
        this.updateHeight(tempNode.right);
        this.updateHeight(tempNode);

        return tempNode;
    }

    private Node<T> balance(Node<T> node){
        int balance = this.getBalance(node);

        if (balance > 1){
            if (this.getBalance(node.left) < 0){
                node.left = this.leftRotation(node.left);
            }
            node = this.rightRotation(node);
        }

        if (balance < -1){
            if (this.getBalance(node.right) > 0){
                node.right = this.rightRotation(node.right);
            }
            node = this.leftRotation(node);
        }
        return node;
    }

    public void delete(T item) {
        throw new UnsupportedOperationException();
    }

    public void deleteMin() {
        throw new UnsupportedOperationException();
    }

    // BONUS
    public void deleteMax() {
        throw new UnsupportedOperationException();
    }

    private int getBalance(Node<T> node){
        return this.height(node.left) - this.height(node.right);
    }

    public Iterable<T> maxElements(int count){
        List<T> result = new ArrayList<>();

        return result;
    }

    public Iterable<T> minElements(int count){
        List<T> result = new ArrayList<>();
        return result;
    }
}
