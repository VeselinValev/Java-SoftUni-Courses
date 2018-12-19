import java.util.Iterator;
import java.util.List;

public class Lake implements Iterable<Integer>{

    private List<Integer> numbers;

    public Lake(List<Integer> numbers) {
        this.setNumbers(numbers);
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }

    private void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Frog();
    }

    private final class Frog implements Iterator<Integer>{
        private int index;

        public Frog() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            if (this.index % 2 == 0 && this.index >= getNumbers().size()){
                this.index = 1;
            }
            return this.index < getNumbers().size();
        }

        @Override
        public Integer next() {
                Integer temp = getNumbers().get(this.index);
                this.index += 2;
                return temp;
        }
    }
}
