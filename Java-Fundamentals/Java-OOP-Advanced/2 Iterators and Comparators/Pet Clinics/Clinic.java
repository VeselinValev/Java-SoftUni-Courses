import java.util.Iterator;

public class Clinic implements Iterable<Pet> {
    private String name;
    private Pet[] rooms;

    public Clinic(String name, int rooms) {
        this.setName(name);
        this.setRooms(rooms);
    }

    public Pet[] getRooms() {
        return this.rooms;
    }

    private void setRooms(int rooms) {
        if (rooms % 2 == 0) {
            throw new IllegalArgumentException("Invalid Operation!");
        }
        this.rooms = new Pet[rooms];
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public boolean hasEmptyRooms() {
        for (Pet pet : this.rooms) {
            if (pet == null) {
                return true;
            }
        }
        return false;
    }

    public boolean add(Pet pet){
        Iterator<Integer> iterator = new IteratorForAdding();
        while (iterator.hasNext()){
            int index = iterator.next();
            if (this.rooms[index] == null){
                this.rooms[index] = pet;
                return true;
            }
        }
        return false;
    }

    public boolean release(){
        Iterator<Integer> iterator = new IteratorForReleasing();
        while (iterator.hasNext()){
            int index = iterator.next();
            if (this.rooms[index] != null){
                this.rooms[index] = null;
                return true;
            }
        }
        return false;
    }

    public String printRoom(int roomNumber){
        String result = "";
        if (this.getRooms()[roomNumber - 1] == null){
            result = "Room empty";
        }else{
            result = this.getRooms()[roomNumber - 1].toString();
        }
        return result;
    }

    public String printAllRooms(){
        StringBuilder sb = new StringBuilder();
        for (Pet pet: this.rooms){
            if (pet == null){
                sb.append("Room empty\n");
            }else{
                sb.append(pet.toString()).append("\n");
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    @Override
    public Iterator<Pet> iterator() {
        return new RoomsIterator();
    }

    private final class RoomsIterator implements Iterator<Pet> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < rooms.length;
        }

        @Override
        public Pet next() {
            return rooms[index++];
        }
    }

    private final class IteratorForAdding implements Iterator<Integer> {
        private int index;
        private int[] indicesOrder;

        public IteratorForAdding() {
            this.index = 0;
            this.setIndicesOrder();
        }

        private void setIndicesOrder() {
            this.indicesOrder = new int[getRooms().length];
            int leftIndex = getRooms().length/2 - 1;
            int rightIndex = getRooms().length/2 + 1;
            this.indicesOrder[0] = getRooms().length / 2;
            for (int i = 1; i < getRooms().length; i++) {
                if (i % 2 != 0) {
                    this.indicesOrder[i] = leftIndex--;
                }else{
                    this.indicesOrder[i] = rightIndex++;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return this.index >= 0 && this.index < getRooms().length;
        }

        @Override
        public Integer next() {
            return this.indicesOrder[index++];
        }
    }

    private final class IteratorForReleasing implements Iterator<Integer> {
        private int index;
        private int[] indicesOrder;

        public IteratorForReleasing() {
            this.index = 0;
            this.setIndicesOrder();
        }

        private void setIndicesOrder() {
            this.indicesOrder = new int[getRooms().length];
            this.indicesOrder[0] = getRooms().length / 2;
            int index = getRooms().length / 2 + 1;
            for (int i = 1; i < getRooms().length; i++) {
                if (index == getRooms().length){
                    index = 0;
                }
                this.indicesOrder[i] = index++;
            }
        }

        @Override
        public boolean hasNext() {
            return this.index >= 0 && this.index < getRooms().length;
        }

        @Override
        public Integer next() {
            return this.indicesOrder[index++];
        }
    }
}
