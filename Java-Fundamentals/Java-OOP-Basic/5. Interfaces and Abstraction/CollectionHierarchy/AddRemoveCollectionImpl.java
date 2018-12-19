package CollectionHierarchy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class AddRemoveCollectionImpl implements AddRemoveCollection{
    private ArrayDeque<String> collection;
    private List<Integer> indices;
    private List<String> removed;

    public AddRemoveCollectionImpl() {
        this.setCollection(new ArrayDeque<>());
        this.setIndices(new ArrayList<>());
        this.setRemoved(new ArrayList<>());
    }
    @Override
    public List<String> getRemoved() {
        return removed;
    }

    private void setRemoved(List<String> removed) {
        this.removed = removed;
    }

    public ArrayDeque<String> getCollection() {
        return collection;
    }

    private void setCollection(ArrayDeque<String> collection) {
        this.collection = collection;
    }

    @Override
    public List<Integer> getIndices() {
        return indices;
    }

    private void setIndices(List<Integer> indices) {
        this.indices = indices;
    }

    @Override
    public int addToCollection(String item) {
        this.collection.addFirst(item);
        return 0;
    }

    @Override
    public void addIndex(int index){
        this.indices.add(index);
    }
    @Override
    public void addRemoved(String item){
        this.removed.add(item);
    }

    @Override
    public String removeFromCollection() {
        return this.collection.pollLast();
    }
}
