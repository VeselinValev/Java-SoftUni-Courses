package enums;

public enum EmeraldEnum {
    STRENGTH(1), AGILITY(4), VITALITY(9);

    private int value;


    EmeraldEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
