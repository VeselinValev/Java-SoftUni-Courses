package exerciseOOP;

public class Car extends Vehicle {
    public Car(double fuelQuantity, double fuelConsumptionPerKM) {
        super(fuelQuantity, fuelConsumptionPerKM);
    }

    @Override
    protected void setFuelConsumptionPerKM(double fuelConsumptionPerKM) {
        super.setFuelConsumptionPerKM(fuelConsumptionPerKM + 0.9);
    }
}
