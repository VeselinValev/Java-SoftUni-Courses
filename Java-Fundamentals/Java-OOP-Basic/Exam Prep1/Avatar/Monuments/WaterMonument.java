package Avatar.Monuments;

public class WaterMonument extends Monument {
    private int waterAffinity;

    public WaterMonument(String name, int waterAffinity) {
        super(name);
        this.setWaterAffinity(waterAffinity);
        super.changePowerBoost(waterAffinity);
    }

    public int getWaterAffinity() {
        return waterAffinity;
    }

    private void setWaterAffinity(int waterAffinity) {
        this.waterAffinity = waterAffinity;
    }

    @Override
    public String toString() {
        return String.format("###Water Monument: %s, Water Affinity: %s%n", super.getName(), this.getWaterAffinity());
    }

}
