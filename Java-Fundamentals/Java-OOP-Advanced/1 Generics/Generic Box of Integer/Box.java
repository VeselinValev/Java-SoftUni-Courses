public class Box<T> {
    private T item;

    public Box(T item) {
        this.setItem(item);
    }

    private void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return this.item;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.getItem().getClass().getName(), this.getItem());
    }
}
