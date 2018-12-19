public class Tuple<T,E,C> {
    private T itemOne;
    private E itemTwo;
    private C itemThree;

    public Tuple(T itemOne, E itemTwo, C itemThree) {
        this.setItemOne(itemOne);
        this.setItemTwo(itemTwo);
        this.setItemThree(itemThree);
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

    public C getItemThree() {
        return this.itemThree;
    }

    private void setItemThree(C itemThree) {
            this.itemThree = itemThree;
    }
}
