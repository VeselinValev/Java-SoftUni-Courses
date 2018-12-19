import java.util.Comparator;

public class PersonComparatorByName implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        int compare = Integer.compare(p1.getName().length(), p2.getName().length());
        if (compare == 0){
            compare = p1.getName().substring(0, 1).toLowerCase()
                    .compareTo(p2.getName().substring(0, 1).toLowerCase());
        }
        return compare;
    }
}
