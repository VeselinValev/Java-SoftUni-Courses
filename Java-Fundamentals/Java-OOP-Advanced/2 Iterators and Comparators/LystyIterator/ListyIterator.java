import java.util.List;
import java.util.NoSuchElementException;

public class ListyIterator {
    private List<String> items;
    private int currentIndex;

    public ListyIterator(List<String> items) {
        this.setItems(items);
        this.setCurrentIndex(0);
    }

    public List<String> getItems() {
        return this.items;
    }

    private void setItems(List<String> items) {
        this.items = items;
    }

    public int getCurrentIndex() {
        return this.currentIndex;
    }

    private void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public boolean move(){
        if (this.getItems().size() - 1 > this.getCurrentIndex()){
            this.currentIndex++;
            return true;
        }
        return false;
    }

    public boolean hasNext(){
        if (this.getItems().size() - 1 > this.getCurrentIndex()){
            return true;
        }
        return false;
    }

    public void print(){
        if (this.getItems().isEmpty()){
            throw new NoSuchElementException("Invalid Operation!");
        }
        System.out.println(this.getItems().get(this.getCurrentIndex()));

    }
}
