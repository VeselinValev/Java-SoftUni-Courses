package exerciseOOP;

public class Truck extends Vehicle{
    public Truck(double fuelQuantity, double fuelConsumptionPerKM, double tankCapacity) {
        super(fuelQuantity, fuelConsumptionPerKM, tankCapacity);
    }

    @Override
    protected void setFuelConsumptionPerKM(double fuelConsumptionPerKM) {
        super.setFuelConsumptionPerKM(fuelConsumptionPerKM + 1.6);
    }

    @Override
    protected void refuel(double amount){
        if(amount <= 0){
            throw  new IllegalArgumentException("Fuel must be a positive number");
        }
        if(super.getFuelQuantity() + amount > super.getTankCapacity()){
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
        super.setFuelQuantity(super.getFuelQuantity() + (amount * 0.95)) ;
    }
}
