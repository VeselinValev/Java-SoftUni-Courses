package weapons;

import enums.SwordAttributes;

public class Sword extends WeaponsImpl{
    public Sword(String name) {
        super(name);
        this.setMinDmg(SwordAttributes.SWORD_MIN_DMG.getValue());
        this.setMaxDmg(SwordAttributes.SWORD_MAX_DMG.getValue());
        this.setGems(SwordAttributes.NUMBER_OF_SOCKETS.getValue());
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
