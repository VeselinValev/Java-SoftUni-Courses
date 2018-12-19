package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.factories;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.constants.Constants;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boatEngines.BoatEngine;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boatEngines.JetEngine;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boatEngines.SterndriveEngine;

public class BoatEngineFactory {

    public BoatEngine getEngine(String... args) {
        String model = args[0];
        String type = args[3];
        switch (type) {
            case "Jet":
                return new JetEngine(model, Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            case "Sterndrive":
                return new SterndriveEngine(model, Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            default:
                throw new IllegalArgumentException(Constants.INCORRECT_ENGINE_TYPE_MESSAGE);
        }
    }
}
