import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {

    private int size;
    public ListNode<T> head;
    public ListNode<T> tail;


    public int getSize() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void add(T element) {
        if (this.tail == null){
            this.tail = this.head = new ListNode<>(element);
        }else{
            ListNode<T> newTail = new ListNode<>(element);
            this.tail.nextNode = newTail;
            this.tail = newTail;
        }
        this.size++;
    }

    public boolean remove(T element) {
        if (this.size == 1){
            this.size--;
            this.head = this.tail = null;
            return true;
        }
        if (this.head.value.equals(element)){
            this.head = this.head.nextNode;
            this.size--;
            return true;
        }
        ListNode<T> currentElement = this.head;
        ListNode<T> previousElement = this.head;
        while(currentElement != null){
            if (currentElement.value.equals(element)){
                if (currentElement.nextNode == null) {
                    this.tail = previousElement;
                    previousElement.nextNode = this.tail;
                    this.size--;
                    return true;
                }
                previousElement.nextNode = currentElement.nextNode;
                this.size--;
                return true;
            }
            previousElement = currentElement;
            currentElement = currentElement.nextNode;
        }
        return false;
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

    private class ListNode<T>{
        public T value;
        public ListNode<T> nextNode;

        public ListNode(T value) {
            this.value = value;
        }
    }
}
