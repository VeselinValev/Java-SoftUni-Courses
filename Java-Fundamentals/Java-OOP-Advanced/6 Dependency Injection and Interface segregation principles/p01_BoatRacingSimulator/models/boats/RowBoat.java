package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boats;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.Race;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.utils.Validator;

public class RowBoat extends BaseBoat {

    private int oars;

    public RowBoat(String model, int weight, int oars) {
        super(model, weight);
        this.setOars(oars);
    }

    @Override
    public double calculateRaceSpeed(Race race) {
        return this.oars * 100 - super.getWeight() + race.getOceanCurrentSpeed();
    }

    private void setOars(int oars) {
        Validator.validatePropertyValue(oars, "Oars");
        this.oars = oars;
    }
}
