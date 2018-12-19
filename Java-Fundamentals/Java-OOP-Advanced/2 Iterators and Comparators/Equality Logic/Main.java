import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Set<Person> treeSet = new TreeSet<>();
        Set<Person> hashSet = new HashSet<>();
        for (int i = 0; i < n; i++){
            String[] input = reader.readLine().split(" ");
            treeSet.add(new Person(input[0], Integer.parseInt(input[1])));
            hashSet.add(new Person(input[0], Integer.parseInt(input[1])));
        }
        System.out.println(treeSet.size());
        System.out.println(hashSet.size());
    }
}