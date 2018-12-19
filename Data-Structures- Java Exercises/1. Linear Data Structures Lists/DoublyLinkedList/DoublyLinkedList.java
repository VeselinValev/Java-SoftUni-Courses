import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class DoublyLinkedList<T> implements Iterable<T> {

    private int size;
    public ListNode<T> head;
    public ListNode<T> tail;


    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void addFirst(T element) {
        if (this.head == null){
            this.head = this.tail = new ListNode<T>(element);
        }else{
            ListNode<T> newHead = new ListNode<T>(element);
            newHead.nextNode = this.head;
            this.head.prevNode = newHead;
            this.head = newHead;
        }
        this.size++;
    }

    public void addLast(T element) {
        if (this.tail == null){
            this.tail = this.head = new ListNode<>(element);
        }else{
            ListNode<T> newTail = new ListNode<>(element);
            newTail.prevNode = this.tail;
            this.tail.nextNode = newTail;
            this.tail = newTail;
        }
        this.size++;
    }

    public T removeFirst() {
        if (this.size == 0){
            throw new IllegalArgumentException("List empty");
        }
        T element = this.head.value;
        if (this.size == 1){
            this.head = this.tail = null;
        }else{
            ListNode<T> newHead = this.head.nextNode;
            newHead.prevNode = null;
            this.head = newHead;
        }
        this.size--;
        return element;
    }

    public T removeLast() {
        if (this.size == 0){
            throw new IllegalArgumentException("List empty");
        }
        T element = this.tail.value;
        if (this.size == 1){
            this.head = this.tail = null;
        }else{
            ListNode<T> newTail = this.tail.prevNode;
            newTail.nextNode = null;
            this.tail = newTail;
        }
        this.size--;
        return element;
    }

    public T[] toArray() {
        T[] array = (T[]) new Object[size];
            ListNode<T> currentNode = this.head;
            for (int i = 0; i < this.size; i++){
                array[i] = currentNode.value;
                currentNode = currentNode.nextNode;
            }
        return array;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    public class ListIterator implements Iterator<T>{

        private ListNode<T> currentNode;

        public ListIterator() {
            this.currentNode = head;
        }

        @Override
        public boolean hasNext() {
            if (currentNode == null){
                return false;
            }
            return true;
        }

        @Override
        public T next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            T element = currentNode.value;
            currentNode = currentNode.nextNode;
            return element;
        }
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        ListNode<T> currentNode = this.head;
        while (currentNode != null){
            action.accept(currentNode.value);
            currentNode = currentNode.nextNode;
        }
    }

    private class ListNode<T>{
        public T value;
        public ListNode<T> nextNode;
        public ListNode<T> prevNode;


        public ListNode(T value) {
            this.value = value;
        }
    }
}
