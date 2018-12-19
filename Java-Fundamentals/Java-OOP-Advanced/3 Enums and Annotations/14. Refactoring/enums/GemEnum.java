package enums;

public enum GemEnum {
    RUBY(7, 2, 5), AMETHYST(2, 8, 4), EMERALD(1, 4, 9);

    private int strength;
    private int agility;
    private int vitality;

    GemEnum(int strength, int agility, int vitality) {
        this.strength = strength;
        this.agility = agility;
        this.vitality = vitality;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getAgility() {
        return this.agility;
    }

    public int getVitality() {
        return this.vitality;
    }
}
