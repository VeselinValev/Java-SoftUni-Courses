package firstLastList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FirstLastList<T extends Comparable<T>> implements IFirstLastList<T> {

    private ArrayList<T> elements;
    private AVL<T> avlTree;

    public FirstLastList() {
        this.elements = new ArrayList<T>();
        this.avlTree = new AVL<>();
    }

    @Override
    public void add(T element) {
        this.elements.add(element);
        this.avlTree.insert(element);
    }

    @Override
    public int getCount() {
        return this.elements.size();
    }

    @Override
    public Iterable<T> first(int count) {
        if (count > this.elements.size()){
            throw new IllegalArgumentException();
        }
        return this.elements.subList(0, count);
    }

    @Override
    public Iterable<T> last(int count) {
        if (count > this.elements.size()){
            throw new IllegalArgumentException();
        }
        List<T> result = new ArrayList<>();
        for (int i = this.elements.size() - 1; i >= this.elements.size() - count; i--){
            result.add(this.elements.get(i));
        }
        return result;
    }

    @Override
    public Iterable<T> min(int count) {
        if (count > this.elements.size()){
            throw new IllegalArgumentException();
        }
        return this.elements.stream().sorted(Comparable::compareTo).limit(count).collect(Collectors.toList());
    }

    @Override
    public Iterable<T> max(int count) {
        if (count > this.elements.size()){
            throw new IllegalArgumentException();
        }
        return this.elements.stream().sorted(Comparator.reverseOrder()).limit(count).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        this.elements = new ArrayList<>();
        this.avlTree = new AVL<>();
    }

    @Override
    public int removeAll(T element) {
        int originalSize = this.elements.size();
        this.elements.removeIf(x -> x.compareTo(element) == 0);
        return originalSize - this.elements.size();
    }
}