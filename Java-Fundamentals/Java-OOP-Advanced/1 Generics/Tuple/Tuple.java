public class Tuple<T,E> {
    private T itemOne;
    private E itemTwo;

    public Tuple(T itemOne, E itemTwo) {
        this.setItemOne(itemOne);
        this.setItemTwo(itemTwo);
    }

    public T getItemOne() {
        return this.itemOne;
    }

    private void setItemOne(T itemOne) {
        this.itemOne = itemOne;
    }

    public E getItemTwo() {
        return this.itemTwo;
    }

    private void setItemTwo(E itemTwo) {
        this.itemTwo = itemTwo;
    }
}
