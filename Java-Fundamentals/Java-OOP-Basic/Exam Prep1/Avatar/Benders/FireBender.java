package Avatar.Benders;

public class FireBender extends Bender {
    private double heatAggression;

    public FireBender(String name, int power, double heatAggression) {
        super(name, power);
        this.setHeatAggression(heatAggression);
        super.changeTotalPower(heatAggression * power);
    }

    public double getHeatAggression() {
        return heatAggression;
    }

    private void setHeatAggression(double heatAggression) {
        this.heatAggression = heatAggression;
    }

    @Override
    public String toString() {
        return String.format("###Fire Bender: %s, Power: %s, Heat Aggression: %.2f%n", super.getName(), super.getPower(), this.getHeatAggression());
    }
}
