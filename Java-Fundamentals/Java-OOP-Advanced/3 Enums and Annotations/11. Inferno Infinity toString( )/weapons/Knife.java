package weapons;

import enums.KnifeAttributes;

public class Knife extends WeaponsImpl {
    public Knife(String name) {
        super(name);
    }

    @Override
    protected void setMinDmg(int minDmg) {
        super.setMinDmg(KnifeAttributes.KNIFE_MIN_DMG.getValue());
    }

    @Override
    protected void setMaxDmg(int maxDmg) {
        super.setMaxDmg(KnifeAttributes.KNIFE_MAX_DMG.getValue());
    }

    @Override
    protected void setGems(int numberOfSockets) {
        super.setGems(KnifeAttributes.NUMBER_OF_SOCKETS.getValue());
    }
}
