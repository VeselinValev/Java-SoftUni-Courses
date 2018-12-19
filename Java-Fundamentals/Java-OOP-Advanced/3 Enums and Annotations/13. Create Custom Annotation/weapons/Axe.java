package weapons;

import enums.AxeAttributes;

public class Axe extends WeaponsImpl {
    public Axe(String name) {

        super(name);
        this.setMinDmg(AxeAttributes.AXE_MIN_DMG.getValue());
        this.setMaxDmg(AxeAttributes.AXE_MAX_DMG.getValue());
        this.setGems(AxeAttributes.NUMBER_OF_SOCKETS.getValue());
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
