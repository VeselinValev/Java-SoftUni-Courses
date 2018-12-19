import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> numbers = Arrays.stream(reader.readLine().split("[,\\s]+")).map(Integer::valueOf).collect(Collectors.toList());
        Lake lake = new Lake(numbers);
        String input = reader.readLine();
        StringBuilder sb = new StringBuilder();
        for (Integer number: lake){
            sb.append(number).append(", ");
        }
        System.out.println(sb.substring(0, sb.length() - 2));
    }
}