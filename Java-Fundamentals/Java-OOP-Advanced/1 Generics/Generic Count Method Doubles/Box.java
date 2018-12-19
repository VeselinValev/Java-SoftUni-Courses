public class Box<T extends Comparable<T>> implements Comparable<T> {
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

    @Override
    public int compareTo(T o) {
        if (o.getClass().getSimpleName().equals("Double")){
            return Double.compare((Double)this.item, (Double) o);
        }
        return getItem().compareTo(o);
    }
}
