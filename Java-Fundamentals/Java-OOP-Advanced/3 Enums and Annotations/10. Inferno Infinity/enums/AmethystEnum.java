package enums;

public enum AmethystEnum {
    STRENGTH(2), AGILITY(8), VITALITY(4);

    private int value;


    AmethystEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
