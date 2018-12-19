package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boatEngines;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.constants.Constants;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.Modelable;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.utils.Validator;

public abstract class BoatEngine implements Modelable {

    private String model;
    private int horsepower;
    private int displacement;
    private int cachedOutput;

    protected BoatEngine(String model, int horsepower, int displacement) {
        this.setModel(model);
        this.setHorsepower(horsepower);
        this.setDisplacement(displacement);
    }

    @Override
    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        Validator.validateModelLength(model, Constants.MIN_BOAT_ENGINE_MODEL_LENGTH);
        this.model = model;
    }

    public int getOutput() {
        return this.cachedOutput;
    }

    public void setCachedOutput(int cachedOutput) {
        this.cachedOutput = cachedOutput;
    }

    public int getHorsepower() {
        return this.horsepower;
    }

    public int getDisplacement() {
        return this.displacement;
    }

    private void setHorsepower(int horsepower) {
        Validator.validatePropertyValue(horsepower, "Horsepower");
        this.horsepower = horsepower;
    }

    public void setDisplacement(int displacement) {
        Validator.validatePropertyValue(displacement, "Displacement");
        this.displacement = displacement;
    }

    public int getCachedOutput() {
        return this.cachedOutput;
    }
}
