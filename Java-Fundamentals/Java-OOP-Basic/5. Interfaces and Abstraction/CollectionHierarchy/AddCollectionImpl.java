package CollectionHierarchy;

import java.util.ArrayList;
import java.util.List;

public class AddCollectionImpl implements AddCollection{
    private List<String> collection;
    private List<Integer> indices;

    public AddCollectionImpl() {
        this.setCollection(new ArrayList<>());
        this.setIndices(new ArrayList<>());
    }

    public List<String> getCollection() {
        return collection;
    }

    private void setCollection(List<String> collection) {
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
        this.collection.add(item);
        return this.collection.size() - 1;
    }

    @Override
    public void addIndex(int index){
        this.indices.add(index);
    }
}
