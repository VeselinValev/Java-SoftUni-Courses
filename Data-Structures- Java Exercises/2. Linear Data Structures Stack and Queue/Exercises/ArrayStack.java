public class ArrayStack<T> {

    private static final int INITIAL_CAPACITY = 16;

    private T[] elements;
    private int size;

    public ArrayStack() {
        this.elements = (T[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    public ArrayStack(int capacity) {
        this.elements = (T[]) new Object[capacity];
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(T element) {
        if (this.size == this.elements.length){
            grow();
        }
        this.elements[this.size++] = element;
    }

    public T pop() {
        if (this.size == 0){
            throw new IllegalArgumentException();
        }
        T element = this.elements[this.size - 1];
        this.elements[this.size-- - 1] = null;
        return element;
    }

    public T[] toArray() {
        T[] newArray = (T[]) new Object[this.size];
        for (int i = 0; i < this.size; i++){
            newArray[this.size - 1 - i] = this.elements[i];
        }
        return newArray;
    }

    private void grow() {
        T[] newArray = (T[]) new Object[this.size*2];
        System.arraycopy( this.elements, 0, newArray, 0, this.size);
        this.elements = newArray;
    }
}