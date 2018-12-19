package weapons;

import enums.KnifeAttributes;

public class Knife extends WeaponsImpl {
    public Knife(String name) {

        super(name);
        this.setMinDmg(KnifeAttributes.KNIFE_MIN_DMG.getValue());
        this.setMaxDmg(KnifeAttributes.KNIFE_MAX_DMG.getValue());
        this.setGems(KnifeAttributes.NUMBER_OF_SOCKETS.getValue());
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
