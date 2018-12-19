package Avatar.Benders;

public class EarthBender extends Bender {
    private double groundSaturation;

    public EarthBender(String name, int power, double groundSaturation) {
        super(name, power);
        this.setGroundSaturation(groundSaturation);
        super.changeTotalPower(groundSaturation * power);
    }

    public double getGroundSaturation() {
        return groundSaturation;
    }

    private void setGroundSaturation(double groundSaturation) {
        this.groundSaturation = groundSaturation;
    }

    @Override
    public String toString() {
        return String.format("###Earth Bender: %s, Power: %s, Ground Saturation: %.2f%n", super.getName(), super.getPower(), this.getGroundSaturation());
    }
}
