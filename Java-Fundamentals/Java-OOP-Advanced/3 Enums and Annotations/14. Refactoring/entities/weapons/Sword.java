package entities.weapons;

import contracts.Weapon;
import enums.WeaponEnum;

public class Sword extends WeaponsImpl{
    public Sword(String name) {
        super(name);
        this.setMinDmg(WeaponEnum.SWORD.getMinDmg());
        this.setMaxDmg(WeaponEnum.SWORD.getMaxDmg());
        this.setGems(WeaponEnum.SWORD.getNumberOfSockets());
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

    @Override
    public int compareTo(Weapon o) {
        return Double.compare(this.getLevel(), o.getLevel());
    }
}
