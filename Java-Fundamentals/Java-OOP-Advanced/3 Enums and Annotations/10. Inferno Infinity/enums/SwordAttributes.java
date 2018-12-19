package enums;

public enum SwordAttributes {
    SWORD_MIN_DMG(4), SWORD_MAX_DMG(6), NUMBER_OF_SOCKETS(3);

    private int value;

    SwordAttributes(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
