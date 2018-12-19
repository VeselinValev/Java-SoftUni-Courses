import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Person> allPersons = new ArrayList<>();
        for (String input = reader.readLine(); !input.equals("END"); input = reader.readLine()){
            String[] tokens = input.split(" ");
            allPersons.add(new Person(tokens[0], Integer.parseInt(tokens[1]), tokens[2]));
        }
        int numPerson = Integer.parseInt(reader.readLine());
        Person toCompareWith = allPersons.get(numPerson - 1);
        allPersons.remove(toCompareWith);
        int equalPersons = 0;
        for (Person person: allPersons){
            if (toCompareWith.compareTo(person) == 0){
                equalPersons++;
            }
        }
        if (equalPersons == 0){
            System.out.println("No matches");
        }else{
            System.out.printf("%s %s %s", equalPersons + 1, allPersons.size() - equalPersons, allPersons.size() + 1);
        }
    }
}