package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.core.BoatSimulatorControllerImpl;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.core.CommandHandlerImpl;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.core.Engine;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.BoatSimulatorController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder();
        BoatSimulatorController ctrl = new BoatSimulatorControllerImpl(result);
        CommandHandlerImpl commandHandler = new CommandHandlerImpl(ctrl);
        Engine engine = new Engine(commandHandler, result);
        engine.run();
    }
}
