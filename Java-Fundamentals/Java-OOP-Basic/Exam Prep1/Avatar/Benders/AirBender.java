package Avatar.Benders;

public class AirBender extends Bender {
    private double aerialIntegrity;

    public AirBender(String name, int power, double aerialIntegrity) {
        super(name, power);
        this.setAerialIntegrity(aerialIntegrity);
        super.changeTotalPower(aerialIntegrity * power);
    }

    public double getAerialIntegrity() {
        return aerialIntegrity;
    }

    private void setAerialIntegrity(double aerialIntegrity) {
        this.aerialIntegrity = aerialIntegrity;
    }

    @Override
    public String toString() {
        return String.format("###Air Bender: %s, Power: %s, Aerial Integrity: %.2f%n", super.getName(), super.getPower(), this.getAerialIntegrity());
    }
}
