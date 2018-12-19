package weapons;

import enums.AxeAttributes;

public class Axe extends WeaponsImpl {
    public Axe(String name) {
        super(name);
    }

    @Override
    protected void setMinDmg(int minDmg) {
        super.setMinDmg(AxeAttributes.AXE_MIN_DMG.getValue());
    }

    @Override
    protected void setMaxDmg(int maxDmg) {
        super.setMaxDmg(AxeAttributes.AXE_MAX_DMG.getValue());
    }

    @Override
    protected void setGems(int numberOfSockets) {
        super.setGems(AxeAttributes.NUMBER_OF_SOCKETS.getValue());
    }
}
