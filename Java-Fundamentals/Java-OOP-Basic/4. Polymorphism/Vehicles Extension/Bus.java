package exerciseOOP;

public class Bus extends Vehicle {

    public Bus(double fuelQuantity, double fuelConsumptionPerKM, double tankCapacity) {
        super(fuelQuantity, fuelConsumptionPerKM, tankCapacity);
    }

    protected void driveFull(double distance){
        double fuelNeeded = distance * (super.getFuelConsumptionPerKM() + 1.4);
        super.setFuelQuantity(super.getFuelQuantity() - fuelNeeded);
    }

    protected boolean hasEnoughFuelIfFull(double distance){
        double fuelNeeded = distance * (super.getFuelConsumptionPerKM() + 1.4);
        boolean result = false;
        if (super.getFuelQuantity() >= fuelNeeded){
            result = true;
        }
        return  result;
    }
}
