package enums;

public enum WeaponEnum {
    AXE(5, 10, 4), KNIFE(3, 4, 2), SWORD(4, 6, 3);

    private int minDmg;
    private int maxDmg;
    private int numberOfSockets;

    WeaponEnum(int minDmg, int maxDmg, int numberOfSockets) {
        this.minDmg = minDmg;
        this.maxDmg = maxDmg;
        this.numberOfSockets = numberOfSockets;
    }

    public int getMinDmg() {
        return this.minDmg;
    }

    public int getMaxDmg() {
        return this.maxDmg;
    }

    public int getNumberOfSockets() {
        return this.numberOfSockets;
    }
}
