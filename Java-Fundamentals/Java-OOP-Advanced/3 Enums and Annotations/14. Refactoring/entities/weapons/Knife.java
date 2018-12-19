package entities.weapons;

import enums.WeaponEnum;

public class Knife extends WeaponsImpl {
    public Knife(String name) {

        super(name);
        this.setMinDmg(WeaponEnum.KNIFE.getMinDmg());
        this.setMaxDmg(WeaponEnum.KNIFE.getMaxDmg());
        this.setGems(WeaponEnum.KNIFE.getNumberOfSockets());
    }

    @Override
    protected void setMinDmg(int minDmg) {

        super.setMinDmg(minDmg);
    }

    @Override
    protected void setMaxDmg(int maxDmg) {

        super.setMaxDmg(maxDmg);
    }

    @Override
    protected void setGems(int numberOfSockets) {

        super.setGems(numberOfSockets);
    }
}
