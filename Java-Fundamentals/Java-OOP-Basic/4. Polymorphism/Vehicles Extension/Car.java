package exerciseOOP;

public class Car extends Vehicle {
    public Car(double fuelQuantity, double fuelConsumptionPerKM, double tankCapacity) {
        super(fuelQuantity, fuelConsumptionPerKM, tankCapacity);
    }

    @Override
    protected void setFuelConsumptionPerKM(double fuelConsumptionPerKM) {
        super.setFuelConsumptionPerKM(fuelConsumptionPerKM + 0.9);
    }
}
