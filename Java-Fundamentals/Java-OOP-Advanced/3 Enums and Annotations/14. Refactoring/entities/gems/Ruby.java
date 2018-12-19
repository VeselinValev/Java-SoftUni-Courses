package entities.gems;

import enums.GemEnum;

public class Ruby extends GemImpl{
    public Ruby() {
        this.setStrength(GemEnum.RUBY.getStrength());
        this.setAgility(GemEnum.RUBY.getAgility());
        this.setVitality(GemEnum.RUBY.getVitality());
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
