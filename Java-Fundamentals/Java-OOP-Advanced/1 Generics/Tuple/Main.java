import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        Tuple<String, String> nameAndAddress = new Tuple<>(input[0] + " " + input[1], input[2]);
        input = reader.readLine().split(" ");
        Tuple<String, Integer> nameAndBeers = new Tuple<>(input[0], Integer.parseInt(input[1]));
        input = reader.readLine().split(" ");
        Tuple<Integer, Double> last = new Tuple<>(Integer.parseInt(input[0]), Double.parseDouble(input[1]));
        System.out.printf("%s -> %s%n", nameAndAddress.getItemOne(), nameAndAddress.getItemTwo());
        System.out.printf("%s -> %s%n", nameAndBeers.getItemOne(), nameAndBeers.getItemTwo());
        System.out.printf("%s -> %s%n", last.getItemOne(), last.getItemTwo());
    }
}