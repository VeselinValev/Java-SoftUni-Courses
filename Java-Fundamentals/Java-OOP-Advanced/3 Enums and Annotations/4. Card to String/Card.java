public class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return this.rank;
    }

    public String getSuit() {
        return this.suit;
    }

    @Override
    public String toString() {
        return String.format("Card name: %s of %s; Card power: %s", this.getRank(), this.getSuit(),
                CardSuits.valueOf(this.getSuit()).getValue() + CardRanks.valueOf(this.getRank()).getValue());
    }
}
