package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boats;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boatEngines.BoatEngine;

public abstract class MotorBoat extends BaseBoat {
    private BoatEngine engine;

    protected MotorBoat(String model, int weight, BoatEngine engine) {
        super(model, weight);
        this.engine = engine;
    }

    public BoatEngine getEngine() {
        return this.engine;
    }
}


