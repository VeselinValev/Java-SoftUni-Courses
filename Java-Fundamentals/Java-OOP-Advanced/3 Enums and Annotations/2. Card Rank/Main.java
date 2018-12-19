import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
         BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(reader.readLine() + ":");
         int counter = 0;
         for (CardRanks cardSuits: CardRanks.values()){
             System.out.printf("Ordinal value: %s; Name value: %s%n", counter++, cardSuits);
         }
    }
}
