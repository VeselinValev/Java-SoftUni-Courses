package enums;

public enum KnifeAttributes {
     KNIFE_MIN_DMG(3), KNIFE_MAX_DMG(4), NUMBER_OF_SOCKETS(2);

    private int value;

    KnifeAttributes(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
