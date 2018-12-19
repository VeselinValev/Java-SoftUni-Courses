package exerciseOOP;

public class Truck extends Vehicle{
    public Truck(double fuelQuantity, double fuelConsumptionPerKM) {
        super(fuelQuantity, fuelConsumptionPerKM);
    }

    @Override
    protected void setFuelConsumptionPerKM(double fuelConsumptionPerKM) {
        super.setFuelConsumptionPerKM(fuelConsumptionPerKM + 1.6);
    }

    @Override
    protected void refuel(double amount){
        super.setFuelQuantity(super.getFuelQuantity() + (amount * 0.95)) ;
    }
}
