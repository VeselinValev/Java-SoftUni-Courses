import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Player {
    private String name;
    private List<Card> cardsInHand;

    public Player(String name) {
        this.setName(name);
        this.setCardsInHand(new ArrayList<>());
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public List<Card> getCardsInHand() {
        return Collections.unmodifiableList(this.cardsInHand);
    }

    private void setCardsInHand(List<Card> cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

    public void addCard(Card card){
        this.cardsInHand.add(card);
    }

    public Card getStrongestCard(){
        this.cardsInHand.sort(Comparator.reverseOrder());
        return this.getCardsInHand().get(0);
    }
}
