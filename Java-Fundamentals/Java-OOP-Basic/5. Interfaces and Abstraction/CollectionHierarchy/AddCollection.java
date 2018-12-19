package CollectionHierarchy;

import java.util.List;

public interface AddCollection {
    int addToCollection(String item);
    void addIndex(int index);
    List<Integer> getIndices();
}
