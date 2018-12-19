package Avatar.Benders;

public class WaterBender extends Bender {
    private double waterClarity;

    public WaterBender(String name, int power, double waterClarity) {
        super(name, power);
        this.setWaterClarity(waterClarity);
        super.changeTotalPower(waterClarity * power);
    }

    public double getWaterClarity() {
        return waterClarity;
    }

    private void setWaterClarity(double waterClarity) {
        this.waterClarity = waterClarity;
    }

    @Override
    public String toString() {
        return String.format("###Water Bender: %s, Power: %s, Water Clarity: %.2f%n", super.getName(), super.getPower(), this.getWaterClarity());
    }
}
