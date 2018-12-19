package entities.gems;

import contracts.Gem;

public abstract class GemImpl implements Gem {
    private int strength;
    private int agility;
    private int vitality;

    @Override
    public int getStrength() {
        return this.strength;
    }
    @Override
    public int getAgility() {
        return this.agility;
    }
    @Override
    public int getVitality() {
        return this.vitality;
    }

    protected void setStrength(int strength) {
        this.strength = strength;
    }

    protected void setAgility(int agility) {
        this.agility = agility;
    }

    protected void setVitality(int vitality) {
        this.vitality = vitality;
    }
}
