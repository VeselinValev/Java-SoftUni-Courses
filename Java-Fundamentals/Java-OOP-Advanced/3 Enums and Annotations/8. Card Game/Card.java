public class Card implements Comparable<Card>{
    private String rank;
    private String suit;
    private int power;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
        this.setPower(rank, suit);
    }

    public int getPower() {
        return this.power;
    }

    private void setPower(String rank, String suit) {
        this.power = CardRanks.valueOf(this.getRank()).getValue() + CardSuits.valueOf(this.getSuit()).getValue();
    }

    public String getRank() {
        return this.rank;
    }

    public String getSuit() {
        return this.suit;
    }

    @Override
    public String toString() {
        return String.format("%s of %s", this.getRank(), this.getSuit());
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(this.getPower(), o.getPower());
    }
}
