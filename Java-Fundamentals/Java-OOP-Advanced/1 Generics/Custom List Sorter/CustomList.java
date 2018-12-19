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

    public int count(){
        return this.list.size();
    }

    public T get(int index){
        return this.list.get(index);
    }

    public void set(int index, T element){
        this.list.set(index, element);
    }

    public int countGreaterThan(T element){
        int count = 0;
        for (int i = 0; i < this.list.size(); i++){
            if (this.list.get(i).compareTo(element) > 0){
                count++;
            }
        }
        return count;
    }

    public boolean contains(T element){
        return this.list.contains(element);
    }

    public void swap(int firstIndex, int secondIndex){
        if (firstIndex < 0 || secondIndex >= this.list.size()){
            throw new IllegalArgumentException();
        }
        T temp = this.list.get(secondIndex);
        this.list.set(secondIndex, this.list.get(firstIndex));
        this.list.set(firstIndex, temp);
    }

    public T getMax(){
        T max = this.list.get(0);
        for (int i = 1; i < this.list.size(); i++){
            if (max.compareTo(this.list.get(i)) < 0){
                max = this.list.get(i);
            }
        }
        return max;
    }

    public T getMin(){
        T min = this.list.get(0);
        for (int i = 1; i < this.list.size(); i++){
            if (min.compareTo(this.list.get(i)) > 0){
                min = this.list.get(i);
            }
        }
        return min;
    }

    public void remove(int index){
        this.list.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++){
            sb.append(this.list.get(i)).append("\n");
        }
        return sb.substring(0, sb.length() - 1);
    }
}