import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cardRank1 = reader.readLine();
        String cardSuit1 = reader.readLine();
        Card card1 = new Card(cardRank1, cardSuit1);
        String cardRank2 = reader.readLine();
        String cardSuit2 = reader.readLine();
        Card card2 = new Card(cardRank2, cardSuit2);
        if (card1.compareTo(card2) > 0){
            System.out.println(card1.toString());
        }else{
            System.out.println(card2.toString());
        }

    }
}
