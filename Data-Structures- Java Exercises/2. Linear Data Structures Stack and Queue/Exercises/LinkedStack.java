public class LinkedStack<E> {

    private Node<E> firstNode;
    private int size;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(E element) {
        Node<E> newNode = new Node<E>(element);
        if (this.size++ == 0){
            this.firstNode = newNode;
        }else{
            newNode.nextNode = this.firstNode;
            this.firstNode = newNode;
        }
    }

    public E pop() {
        if (this.size == 0){
            throw new IllegalArgumentException();
        }
        E element = this.firstNode.value;
        this.firstNode = this.firstNode.nextNode;
        this.size--;
        return element;
    }

    public E[] toArray() {
        E[] array = (E[]) new Object[this.size];
        Node<E> temp = firstNode;
        for (int i = 0; i < this.size; i++){
            array[i] = temp.value;
            temp = temp.nextNode;
        }
        return array;
    }

    private class Node<E> {

        private E value;
        private Node<E> nextNode;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }

        public Node<E> getNextNode() {

            return this.nextNode;
        }

        public void setNextNode(Node<E> nextNode) {

            this.nextNode = nextNode;
        }
    }
}