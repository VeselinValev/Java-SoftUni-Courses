@EnumInfo(type = "Enumeration", category = "Suit", description = "Provides suit constants for a Card class.")
public enum CardSuits {
    CLUBS(0), DIAMONDS(13), HEARTS(26), SPADES(39);

    private int value;

    CardSuits(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
