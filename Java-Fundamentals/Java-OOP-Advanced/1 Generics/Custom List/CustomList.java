import java.util.ArrayList;
import java.util.List;

public class CustomList<T extends Comparable<T>>  {
    private List<T> list;

    public CustomList() {
        this.list = new ArrayList<>();
    }

    public void add(T item){
        this.list.add(item);
    }

    public int countGreaterThan(T element){
        int count = 0;
        for (int i = 0; i < this.list.size(); i++){
            if (list.get(i).compareTo(element) > 0){
                count++;
            }
        }
        return count;
    }

    public boolean contains(T element){
        return list.contains(element);
    }

    public void swap(int firstIndex, int secondIndex){
        if (firstIndex < 0 || secondIndex >= this.list.size()){
            throw new IllegalArgumentException();
        }
        T temp = list.get(secondIndex);
        list.set(secondIndex, list.get(firstIndex));
        list.set(firstIndex, temp);
    }

    public T getMax(){
        T max = list.get(0);
        for (int i = 1; i < this.list.size(); i++){
            if (max.compareTo(list.get(i)) < 0){
                max = list.get(i);
            }
        }
        return max;
    }

    public T getMin(){
        T min = list.get(0);
        for (int i = 1; i < this.list.size(); i++){
            if (min.compareTo(list.get(i)) > 0){
                min = list.get(i);
            }
        }
        return min;
    }

    public void remove(int index){
        list.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++){
            sb.append(list.get(i)).append("\n");
        }
        return sb.substring(0, sb.length() - 1);
    }
}