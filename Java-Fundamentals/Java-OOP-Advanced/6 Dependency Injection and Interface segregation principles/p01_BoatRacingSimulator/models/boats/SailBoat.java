package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boats;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.constants.Constants;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.Race;

public class SailBoat extends BaseBoat {

    private int sailEfficiency;

    public SailBoat(String model, int weight, int sailEfficiency) {
        super(model, weight);
        this.setSailEfficiency(sailEfficiency);
    }

    @Override
    public double calculateRaceSpeed(Race race) {
        return race.getWindSpeed() * (this.sailEfficiency / 100.0) - super.getWeight() + (race.getOceanCurrentSpeed() / 2.0);
    }

    private void setSailEfficiency(int sailEfficiency) {
        if (sailEfficiency < 1 || sailEfficiency > 100) {
            throw new IllegalArgumentException(Constants.INCORRECT_SAIL_EFFICIENCY_MESSAGE);
        }
        this.sailEfficiency = sailEfficiency;
    }
}
