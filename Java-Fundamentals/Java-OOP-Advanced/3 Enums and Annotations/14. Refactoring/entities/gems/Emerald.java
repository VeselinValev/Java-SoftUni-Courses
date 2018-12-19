package entities.gems;

import enums.GemEnum;

public class Emerald extends GemImpl {
    public Emerald() {
        this.setStrength(GemEnum.EMERALD.getStrength());
        this.setAgility(GemEnum.EMERALD.getAgility());
        this.setVitality(GemEnum.EMERALD.getVitality());
    }

    @Override
    protected void setStrength(int strength) {
        super.setStrength(strength);
    }

    @Override
    protected void setAgility(int agility) {
        super.setAgility(agility);
    }

    @Override
    protected void setVitality(int vitality) {
        super.setVitality(vitality);
    }
}
