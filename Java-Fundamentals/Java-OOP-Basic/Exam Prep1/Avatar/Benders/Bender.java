package Avatar.Benders;

public abstract class Bender {
    private String name;
    private int power;
    private double totalPower;

    public Bender(String name, int power) {
        this.setName(name);
        this.setPower(power);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    private void setPower(int power) {
        this.power = power;
    }

    public double getTotalPower() {
        return totalPower;
    }

    private void setTotalPower(double totalPower) {
        this.totalPower = totalPower;
    }

    protected void changeTotalPower(double totalPower){
        this.setTotalPower(totalPower);
    }
}
