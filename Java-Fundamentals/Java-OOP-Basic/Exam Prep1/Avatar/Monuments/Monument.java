package Avatar.Monuments;

public abstract class Monument {
    private String name;
    private int powerBoost;

    public Monument(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getPowerBoost() {
        return powerBoost;
    }

    private void setPowerBoost(int powerBoost) {
        this.powerBoost = powerBoost;
    }

    protected void changePowerBoost(int powerBoost){
        this.setPowerBoost(powerBoost);
    }
}
