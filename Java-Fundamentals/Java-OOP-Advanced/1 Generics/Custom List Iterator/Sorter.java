import java.util.ArrayList;
import java.util.List;

public class Sorter<T extends Comparable<T>> {
    private CustomList<T> list;

    public Sorter(CustomList<T> list) {
        this.list = list;
    }

    public CustomList<T> sort(){
        List<T> toSort = new ArrayList<>();
        for (int i = 0; i < this.list.count(); i++){
            toSort.add(this.list.get(i));
        }
        toSort.sort(Comparable::compareTo);
        for (int i = 0; i < this.list.count(); i++){
            this.list.set(i, toSort.get(i));
        }
        return this.list;
    }
}
