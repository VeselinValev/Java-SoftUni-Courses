package main;

import java.util.*;

public class Hierarchy<T> implements IHierarchy<T> {

    private Map<T, List<T>> elementChildrenMap;
    private Map<T, T> childParentMap;
    private int size;
    private T root;

    public Hierarchy(T element){
        this.elementChildrenMap = new LinkedHashMap<>();
        this.childParentMap = new LinkedHashMap<>();
        this.elementChildrenMap.put(element, new ArrayList<>());
        this.childParentMap.put(element, null);
        this.root = element;
        this.size = 1;
    }

    public void add(T parent, T child){
        if (!elementChildrenMap.containsKey(parent)){
            throw new IllegalArgumentException();
        }
        if (this.elementChildrenMap.containsKey(child)){
            throw new IllegalArgumentException();
        }
        this.elementChildrenMap.get(parent).add(child);
        this.elementChildrenMap.put(child, new ArrayList<>());
        this.childParentMap.put(child, parent);
        this.size++;
    }

    public int getCount() {
        return this.size;
    }

    public void remove(T element){
        if (!this.childParentMap.containsKey(element)){
            throw new IllegalArgumentException();
        }
        if (this.childParentMap.get(element) == null){
            throw new IllegalStateException();
        }
        this.elementChildrenMap.get(this.childParentMap.get(element)).addAll(this.elementChildrenMap.get(element));
        for (T item: this.elementChildrenMap.get(element)){
            this.childParentMap.put(item, this.childParentMap.get(element));
        }
        this.elementChildrenMap.get(this.childParentMap.get(element)).remove(element);
        this.elementChildrenMap.remove(element);
        this.childParentMap.remove(element);
        this.size--;
    }

    public boolean contains(T element){
        return this.childParentMap.containsKey(element);
    }

    public T getParent(T element){
        if (!this.childParentMap.containsKey(element)){
            throw new IllegalArgumentException();
        }
        return this.childParentMap.get(element);
    }

    public Iterable<T> getChildren(T element){
        if (!this.elementChildrenMap.containsKey(element)){
            throw new IllegalArgumentException();
        }
        return this.elementChildrenMap.get(element);
    }

    public Iterable<T> getCommonElements(IHierarchy<T> other){
        List<T> commonElements = new ArrayList<>();
        for (T element: this.elementChildrenMap.keySet()){
            if (other.contains(element)){
                commonElements.add(element);
            }
        }
        return commonElements;
    }

    @Override
    public Iterator<T> iterator() {
        List<T> allElements = new ArrayList<>();
        ArrayDeque<T> queue = new ArrayDeque<>();
        queue.push(this.root);
        while (!queue.isEmpty()){
            T currentElement = queue.pollFirst();
            allElements.add(currentElement);
            queue.addAll(this.elementChildrenMap.get(currentElement));
        }
        return allElements.iterator();
    }
}
