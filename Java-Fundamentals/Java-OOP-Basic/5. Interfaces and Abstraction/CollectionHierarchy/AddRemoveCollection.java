package CollectionHierarchy;

import java.util.List;

public interface AddRemoveCollection extends AddCollection{
    String removeFromCollection();
    void addRemoved(String item);
    List<String> getRemoved();
}
