import Cars.Car;

import java.util.Map;

public class Garage {
    private Map<Integer, Car> parkedCars;

    public Garage(Map<Integer, Car> parkedCars) {
        this.setParkedCars(parkedCars);
    }

    public  Map<Integer, Car> getParkedCars() {
        return this.parkedCars;
    }

    private void setParkedCars(Map<Integer, Car> parkedCars) {
        this.parkedCars = parkedCars;
    }
}
