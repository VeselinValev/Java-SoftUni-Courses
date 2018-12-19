package Avatar.Monuments;

public class EarthMonument extends Monument {
    private int earthAffinity;

    public EarthMonument(String name, int earthAffinity) {
        super(name);
        this.setEarthAffinity(earthAffinity);
        super.changePowerBoost(earthAffinity);
    }

    public int getEarthAffinity() {
        return earthAffinity;
    }

    private void setEarthAffinity(int earthAffinity) {
        this.earthAffinity = earthAffinity;
    }

    @Override
    public String toString() {
        return String.format("###Earth Monument: %s, Earth Affinity: %s%n", super.getName(), this.getEarthAffinity());
    }
}
