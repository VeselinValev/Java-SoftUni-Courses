package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.commands;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.BoatSimulatorController;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.Executable;

public abstract class BaseCommand implements Executable {

    private BoatSimulatorController controller;

    protected BaseCommand(BoatSimulatorController controller) {
        this.controller = controller;
    }

    protected BoatSimulatorController getController() {
        return this.controller;
    }
}
