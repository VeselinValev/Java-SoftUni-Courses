package entities.gems;

import enums.GemEnum;

public class Amethyst extends GemImpl {
    public Amethyst() {
        this.setStrength(GemEnum.AMETHYST.getStrength());
        this.setAgility(GemEnum.AMETHYST.getAgility());
        this.setVitality(GemEnum.AMETHYST.getVitality());
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
