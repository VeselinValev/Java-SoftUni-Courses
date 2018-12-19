import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    private List<T> heap;

    public BinaryHeap() {

        this.heap = new ArrayList<>();
    }

    public int size() {
        return this.heap.size();
    }

    public void insert(T element) {
        this.heap.add(element);
        this.heapifyUp(this.size() - 1);
    }

    private void heapifyUp(int index) {
        T element = this.heap.get(index);
        T parent = this.heap.get((index - 1) / 2);

        while (element.compareTo(parent) > 0) {
            this.swapElements(index, (index - 1) / 2);
            index = (index - 1) / 2;
            parent = this.heap.get((index - 1) / 2);
        }
    }

    private void swapElements(int elementIndex, int parentIndex) {
        T temp = this.heap.get(parentIndex);
        this.heap.set(parentIndex, this.heap.get(elementIndex));
        this.heap.set(elementIndex, temp);
    }

    public T peek() {
        return this.heap.get(0);
    }

    public T pull() {
        if (this.size() == 0) {
            throw new IllegalArgumentException("Heap is empty");
        }
        T result = this.heap.get(0);
        this.swapElements(0, this.size()-1);
        this.heap.remove(this.size()-1);
        this.heapifyDown(0);
        return result;
    }

    private void heapifyDown(int index) {
        while(index < this.size()/2){
            T element = this.heap.get(index);
            int child = index*2 + 1;
            if (this.size() > index*2 + 2 && this.heap.get(index*2 + 2).compareTo(this.heap.get(child)) > 0){
                child++;
            }
            if (element.compareTo(this.heap.get(child)) < 0){
                swapElements(index, child);
                index = child;
            }else{
                break;
            }
        }
    }
}
