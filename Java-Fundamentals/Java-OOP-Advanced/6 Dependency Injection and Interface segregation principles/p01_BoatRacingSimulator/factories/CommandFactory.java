package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.factories;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.commands.*;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.BoatSimulatorController;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.Executable;

public class CommandFactory {

    public Executable getCommand(String name, BoatSimulatorController controller) {
        switch (name) {
            case "CreateBoatEngine":
                return new CreateBoatEngineCommand(controller);
            case "CreatePowerBoat":
                return new CreatePowerBoatCommand(controller);
            case "CreateRowBoat":
                return new CreateRowBoatCommand(controller);
            case "CreateSailBoat":
                return new CreateSailBoatCommand(controller);
            case "CreateYacht":
                return new CreateYachtCommand(controller);
            case "GetStatistic":
                return new GetStatisticCommand(controller);
            case "OpenRace":
                return new OpenRaceCommand(controller);
            case "SignUpBoat":
                return new SignUpBoatCommand(controller);
            case "StartRace":
                return new StartRaceCommand(controller);
            default:
                return null;
        }
    }
}
