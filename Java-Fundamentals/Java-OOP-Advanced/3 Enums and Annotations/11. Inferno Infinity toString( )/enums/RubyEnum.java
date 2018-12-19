package enums;

public enum RubyEnum {
    STRENGTH(7), AGILITY(2), VITALITY(5);

    private int value;


    RubyEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
