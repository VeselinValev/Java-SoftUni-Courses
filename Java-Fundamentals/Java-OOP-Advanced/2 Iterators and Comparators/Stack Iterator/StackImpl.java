import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack<Integer>, Iterable<Integer> {
    private Integer[] elements;
    private int currentSize;

    public StackImpl() {
        this.setElements(new Integer[16]);
        this.setCurrentSize(0);
    }
    @Override
    public Integer[] getElements() {
        return this.elements;
    }

    private void setElements(Integer[] elements) {
        this.elements = elements;
    }

    public int getCurrentSize() {
        return this.currentSize;
    }

    private void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    @Override
    public void push(Integer element) {
        this.elements[currentSize++] = element;
    }

    @Override
    public int pop() {
        if (this.currentSize == 0){
            throw new NoSuchElementException("No elements");
        }
        int toReturn = this.getElements()[this.currentSize-- - 1];
        this.getElements()[this.currentSize] = null;
        return toReturn;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new StackIterator();
    }

    private final class StackIterator implements Iterator<Integer>{
        private int index;

        public StackIterator() {
            this.index = getCurrentSize() - 1;
        }

        @Override
        public boolean hasNext() {
            return this.index >= 0;
        }

        @Override
        public Integer next() {
            return getElements()[index--];
        }
    }
}
