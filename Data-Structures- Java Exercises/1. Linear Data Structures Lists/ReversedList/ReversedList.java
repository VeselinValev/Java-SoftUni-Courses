import java.util.Iterator;
import java.util.function.Consumer;

public class ReversedList<T> implements Iterable<T> {
    private T[] array;
    private int arrayCapacity;
    private int currentSize;

    public ReversedList() {
        this.array = (T[]) new Object[2];
        this.currentSize = 0;
        this.arrayCapacity = 2;
    }

    private void increaseCapacity() {
        T[] newArray = (T[]) new Object[this.arrayCapacity * 2];
        System.arraycopy(this.array, 0, newArray, 0, this.arrayCapacity);
        this.arrayCapacity *= 2;
        this.array = newArray;
    }

    private boolean checkCapacity(){
        return this.arrayCapacity == this.currentSize;
    }

    private boolean indexValidation(int index){
        return (index < 0 || index >= this.currentSize);
    }

    public void add(T item){
        if (checkCapacity()){
            increaseCapacity();
        }
        this.array[this.currentSize++] = item;
    }

    public int count(){
        return this.currentSize;
    }

    public T get(int index){
        int reversedIndex = getReversedIndex(index);
        return this.array[reversedIndex];
    }

    public void set(int index, T element){
        int reversedIndex = getReversedIndex(index);
        this.array[reversedIndex] = element;
    }

    public void removeAt(int index){
        int reversedIndex = getReversedIndex(index);
        T[] newArray = (T[]) new Object[this.arrayCapacity * 2];
        int toSkip = 0;
        for (int i = 0; i < this.currentSize - 1; i++) {
            if (i == reversedIndex){
                toSkip = 1;
            }
            newArray[i] = this.array[i + toSkip];
        }
        this.array = newArray;
        currentSize--;
    }

    private int getReversedIndex(int index){
        if (indexValidation(index)){
            throw new IndexOutOfBoundsException();
        }
        return this.currentSize - index - 1;
    }

    public int capacity(){
        return this.arrayCapacity;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {
        for (int i = 0; i < this.currentSize; i++){
            action.accept(this.array[i]);
        }
    }
}