public class Card implements Comparable<Card>{
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
        return String.format("%s of %s", this.getRank(), this.getSuit());
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(CardRanks.valueOf(this.getRank()).getValue(), CardRanks.valueOf(o.getRank()).getValue());
    }
}
