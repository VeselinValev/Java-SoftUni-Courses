package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boats;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.Race;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boatEngines.BoatEngine;

public class PowerBoat extends MotorBoat {

    private BoatEngine secondEngine;

    public PowerBoat(String model, int weight, BoatEngine engine, BoatEngine secondEngine) {
        super(model, weight, engine);
        this.secondEngine = secondEngine;
    }

    @Override
    public double calculateRaceSpeed(Race race) {
        return super.getEngine().getOutput() + this.secondEngine.getOutput() - super.getWeight() + (race.getOceanCurrentSpeed() / 5.0);
    }
}
