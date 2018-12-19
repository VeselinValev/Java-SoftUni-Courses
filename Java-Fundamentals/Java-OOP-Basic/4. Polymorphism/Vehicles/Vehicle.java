package exerciseOOP;

public abstract class Vehicle {
    private double fuelQuantity;
    private double fuelConsumptionPerKM;

    protected Vehicle(double fuelQuantity, double fuelConsumptionPerKM) {
        this.setFuelQuantity(fuelQuantity);
        this.setFuelConsumptionPerKM(fuelConsumptionPerKM);
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    protected void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public double getFuelConsumptionPerKM() {
        return fuelConsumptionPerKM;
    }

    protected void setFuelConsumptionPerKM(double fuelConsumptionPerKM) {
        this.fuelConsumptionPerKM = fuelConsumptionPerKM;
    }
    protected void refuel(double amount){
        this.fuelQuantity += amount;
    }
    protected void drive(double distance){
        double fuelNeeded = distance * this.fuelConsumptionPerKM;
        this.fuelQuantity -= fuelNeeded;
    }
    protected boolean hasEnoughFuel(double distance){
        double fuelNeeded = distance * this.fuelConsumptionPerKM;
        boolean result = false;
        if (this.fuelQuantity >= fuelNeeded){
            result = true;
        }
        return  result;
    }
}
