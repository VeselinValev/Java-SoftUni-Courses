import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {

    private int size;
    private Node head;
    private Node tail;

    public LinkedList() {
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public Node getHead() {
        return head;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void addFirst(E item) {
        if (this.size == 0){
            this.head = new Node(item);
            this.tail = this.head;
        }else{
            Node newHead = new Node(item);
            newHead.next = this.head;
            this.head = newHead;
        }
        this.size++;
    }

    public void addLast(E item) {
        Node newNode = new Node(item);
        if (this.size == 0){
            this.head = this.tail = newNode;
        }else{
            this.tail.next = this.tail = newNode;
        }
        this.size++;
    }

    public E removeFirst() {
        if (this.size == 0){
            throw new UnsupportedOperationException("The list is Empty");
        }
        this.size--;
        E result = this.head.value;
        this.head = this.head.next;
        return result;
    }

    public E removeLast() {
        if (this.size == 0){
            throw new UnsupportedOperationException("The list is Empty");
        }

        E result = this.tail.value;
        this.tail = this.getSecondToLast();
        this.tail.next = null;
        this.size--;
        return result;
    }

    private Node getSecondToLast(){
        Node result = this.head;
        for (int i = 0; i < this.size - 2; i++){
            result = result.next;
        }
        System.out.println(result.value);
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    class Node {
        public E value;
        public Node next;

        public Node(E value) {
            this.value = value;
        }
    }

    private class LinkedListIterator implements Iterator<E> {

        private Node current;

        private LinkedListIterator() {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return this.current == tail;
        }

        @Override
        public E next() {
            E value = this.current.value;
            this.current = this.current.next;
            return value;
        }
    }
}
