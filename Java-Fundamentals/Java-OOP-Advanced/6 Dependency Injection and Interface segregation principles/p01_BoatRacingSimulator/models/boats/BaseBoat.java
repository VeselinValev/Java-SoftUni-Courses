package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boats;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.constants.Constants;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.Modelable;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.Race;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.utils.Validator;

public abstract class BaseBoat implements Modelable {

    private String model;
    private int weight;

    protected BaseBoat(String model, int weight) {
        this.setModel(model);
        this.setWeight(weight);
    }

    @Override
    public String getModel() {
        return this.model;
    }

    private void setModel(String model) {
        Validator.validateModelLength(model, Constants.MIN_BOAT_MODEL_LENGTH);
        this.model = model;
    }

    public int getWeight() {
        return this.weight;
    }

    private void setWeight(int weight) {
        Validator.validatePropertyValue(weight, "Weight");
        this.weight = weight;
    }

    public abstract double calculateRaceSpeed(Race race);
}
