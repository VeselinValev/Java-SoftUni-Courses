package exerciseOOP;

public abstract class Vehicle {
    private double fuelQuantity;
    private double fuelConsumptionPerKM;
    private double tankCapacity;

    protected Vehicle(double fuelQuantity, double fuelConsumptionPerKM, double tankCapacity) {
        this.setFuelQuantity(fuelQuantity);
        this.setFuelConsumptionPerKM(fuelConsumptionPerKM);
        this.setTankCapacity(tankCapacity);
    }

    public double getFuelConsumptionPerKM() {
        return fuelConsumptionPerKM;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    protected void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    protected void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    protected void setFuelConsumptionPerKM(double fuelConsumptionPerKM) {
        this.fuelConsumptionPerKM = fuelConsumptionPerKM;
    }
    protected void refuel(double amount){
        if(amount <= 0){
            throw  new IllegalArgumentException("Fuel must be a positive number");
        }
        if(this.fuelQuantity + amount > this.tankCapacity){
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
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
