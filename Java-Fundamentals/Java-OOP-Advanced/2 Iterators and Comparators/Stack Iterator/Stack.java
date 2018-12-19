public interface Stack<Integer> extends Iterable<Integer> {
    void push(Integer element);
    int pop();
    Integer[] getElements();
}
