import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static List<Card> deck = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (CardSuits suit : CardSuits.values()) {
            for (CardRanks rank : CardRanks.values()) {
                deck.add(new Card(rank.name(), suit.name()));
            }
        }
        String player1Name = reader.readLine();
        String player2Name = reader.readLine();
        Player player1 = new Player(player1Name);
        player1 = addCardsInHand(player1, reader);
        Player player2 = new Player(player2Name);
        player2 = addCardsInHand(player2, reader);
        if (player1.getStrongestCard().compareTo(player2.getStrongestCard()) > 0){
            printMessage(String.format("%s wins with %s.", player1Name, player1.getStrongestCard().toString()));
        }else{
            printMessage(String.format("%s wins with %s.", player2Name, player2.getStrongestCard().toString()));
        }
    }
    public static Player addCardsInHand(Player player, BufferedReader reader) throws IOException {
        while (player.getCardsInHand().size() < 5){
            String[] input = reader.readLine().split(" ");
            Card card;
            try{
                card = new Card(CardRanks.valueOf(input[0]).name(), CardSuits.valueOf(input[2]).name());
            }catch (IllegalArgumentException iae){
                printMessage("No such card exists.");
                continue;
            }
            Card cardFinal = card;
            if (deck.stream().anyMatch(x -> x.getRank().equals(cardFinal.getRank()) && x.getSuit().equals(cardFinal.getSuit()))){
                player.addCard(cardFinal);
                deck = deck.stream().filter(x -> !(x.getRank().equals(cardFinal.getRank()) && x.getSuit().equals(cardFinal.getSuit()))).collect(Collectors.toList());
            }else{
                printMessage("Card is not in the deck.");
            }
        }
        return player;
    }

    public static void printMessage(String message){
        System.out.println(message);
    }
}
