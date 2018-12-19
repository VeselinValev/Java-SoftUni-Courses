package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boats;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.Race;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boatEngines.BoatEngine;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.utils.Validator;

public class Yacht extends MotorBoat {

    private int cargoWeight;

    public Yacht(String model, int weight, BoatEngine engine, int cargoWeight) {
        super(model, weight, engine);
        this.setCargoWeight(cargoWeight);
    }

    @Override
    public double calculateRaceSpeed(Race race) {
        return super.getEngine().getOutput() - (super.getWeight() + this.cargoWeight) + (race.getOceanCurrentSpeed() / 2.0);
    }

    private void setCargoWeight(int cargoWeight) {
        Validator.validatePropertyValue(cargoWeight, "Cargo Weight");
        this.cargoWeight = cargoWeight;
    }
}
