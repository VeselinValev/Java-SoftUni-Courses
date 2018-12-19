package weapons;

import enums.SwordAttributes;

public class Sword extends WeaponsImpl{
    public Sword(String name) {
        super(name);
    }
    @Override
    protected void setMinDmg(int minDmg) {
        super.setMinDmg(SwordAttributes.SWORD_MIN_DMG.getValue());
    }

    @Override
    protected void setMaxDmg(int maxDmg) {
        super.setMaxDmg(SwordAttributes.SWORD_MAX_DMG.getValue());
    }

    @Override
    protected void setGems(int numberOfSockets) {
        super.setGems(SwordAttributes.NUMBER_OF_SOCKETS.getValue());
    }
}
