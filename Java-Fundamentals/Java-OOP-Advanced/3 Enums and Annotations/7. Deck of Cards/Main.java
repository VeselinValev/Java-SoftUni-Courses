import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        if (input.equals("Card Deck")){
            for (CardSuits suit: CardSuits.values()){
                for (CardRanks rank: CardRanks.values()){
                    System.out.println(new Card(rank.name(), suit.name()));
                }
            }
        }
    }
}
