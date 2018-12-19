package enums;

public enum AxeAttributes {
    AXE_MIN_DMG(5), AXE_MAX_DMG(10), NUMBER_OF_SOCKETS(4);

    private int value;

    AxeAttributes(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
