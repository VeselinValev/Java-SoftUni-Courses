import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        Tuple<String, String, String> nameAndAddress = new Tuple<>(input[0] + " " + input[1], input[2], input[3]);
        input = reader.readLine().split(" ");
        Tuple<String, Integer, Boolean> nameAndBeers = null;
        if (input[2].equals("drunk")){
            nameAndBeers = new Tuple<>(input[0], Integer.parseInt(input[1]), true);
        }else{
            nameAndBeers = new Tuple<>(input[0], Integer.parseInt(input[1]), false);
        }
        input = reader.readLine().split(" ");
        Tuple<String, Double, String> last = new Tuple<>(input[0], Double.parseDouble(input[1]), input[2]);
        System.out.printf("%s -> %s -> %s%n", nameAndAddress.getItemOne(), nameAndAddress.getItemTwo(), nameAndAddress.getItemThree());
        System.out.printf("%s -> %s -> %s%n", nameAndBeers.getItemOne(), nameAndBeers.getItemTwo(), nameAndBeers.getItemThree());
        System.out.printf("%s -> %s -> %s%n", last.getItemOne(), last.getItemTwo(), last.getItemThree());
    }
}