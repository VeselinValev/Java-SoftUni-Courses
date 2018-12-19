package entities.weapons;

import enums.WeaponEnum;

public class Axe extends WeaponsImpl {
    public Axe(String name) {

        super(name);
        this.setMinDmg(WeaponEnum.AXE.getMinDmg());
        this.setMaxDmg(WeaponEnum.AXE.getMaxDmg());
        this.setGems(WeaponEnum.AXE.getNumberOfSockets());
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
