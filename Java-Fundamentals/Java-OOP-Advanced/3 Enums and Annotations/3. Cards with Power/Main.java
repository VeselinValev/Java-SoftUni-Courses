import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cardRank = reader.readLine();
        String cardSuit = reader.readLine();
        System.out.printf("Card name: %s of %s; Card power: %s%n", cardRank, cardSuit, CardSuits.valueOf(cardSuit).getValue() + CardRanks.valueOf(cardRank).getValue());
    }
}
