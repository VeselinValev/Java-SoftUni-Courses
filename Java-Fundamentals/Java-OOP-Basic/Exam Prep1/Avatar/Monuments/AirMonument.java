package Avatar.Monuments;

public class AirMonument extends Monument{
    private int airAffinity;

    public AirMonument(String name, int airAffinity) {
        super(name);
        this.setAirAffinity(airAffinity);
        super.changePowerBoost(airAffinity);
    }

    public int getAirAffinity() {
        return airAffinity;
    }

    private void setAirAffinity(int airAffinity) {
        this.airAffinity = airAffinity;
    }

    @Override
    public String toString() {
        return String.format("###Air Monument: %s, Air Affinity: %s%n", super.getName(), this.getAirAffinity());
    }
}
