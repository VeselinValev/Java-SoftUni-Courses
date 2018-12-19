public class Heap {

    public static <E extends Comparable<E>> void sort(E[] array) {
        if (array.length > 1){
            for (int i = array.length/2; i >= 0; i--){
                heapifyDown(array, i, array.length);
            }
            for (int i = array.length - 1; i > 0; i--){
                swapElements(array,0, i);
                heapifyDown(array, 0, i);
            }
        }
    }

    private static <E extends Comparable<E>> void heapifyDown(E[] array, int index, int length) {
        while(index < length/2){
            E element = array[index];
            int child = index*2 + 1;
            if (length > index*2 + 2 && array[index*2 + 2].compareTo(array[child]) > 0){
                child++;
            }
            if (element.compareTo(array[child]) < 0){
                swapElements(array, index, child);
                index = child;
            }else{
                break;
            }
        }
    }

    private static <E extends Comparable<E>> void swapElements(E[] array, int elementIndex, int parentIndex) {
        E temp = array[parentIndex];
        array[parentIndex] = array[elementIndex];
        array[elementIndex] = temp;
    }
}
