public class LinkedQueue<E> {

    private int size;
    private QueueNode<E> head;
    private QueueNode<E> tail;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        QueueNode<E> newTail = new QueueNode<>();
        newTail.setValue(element);
        if (this.tail == null){
            this.tail = this.head = newTail;
        }else{
            newTail.prevNode = this.tail;
            this.tail.nextNode = newTail;
            this.tail = newTail;
        }
        this.size++;
    }

    public E dequeue() {
        if (this.size == 0){
            throw new IllegalArgumentException();
        }
        E element = this.head.value;
        if (this.size == 1){
            this.head = this.tail = null;
        }else{
            QueueNode<E> newHead = this.head.nextNode;
            newHead.prevNode = null;
            this.head = newHead;
        }
        this.size--;
        return element;
    }

    public E[] toArray() {
        E[] array = (E[]) new Object[size];
        QueueNode<E> currentNode = this.head;
        for (int i = 0; i < this.size; i++){
            array[i] = currentNode.value;
            currentNode = currentNode.nextNode;
        }
        return array;
    }

    private class QueueNode<E> {
        private E value;

        private QueueNode<E> nextNode;
        private QueueNode<E> prevNode;

        public E getValue() {
            return this.value;
        }

        private void setValue(E value) {
            this.value = value;
        }

        public QueueNode<E> getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(QueueNode<E> nextNode) {
            this.nextNode = nextNode;
        }

        public QueueNode<E> getPrevNode() {
            return this.prevNode;
        }

        public void setPrevNode(QueueNode<E> prevNode) {
            this.prevNode = prevNode;
        }
    }
}