package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.core;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.BoatSimulatorController;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.Executable;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.*;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.factories.CommandFactory;

public class CommandHandlerImpl implements t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.CommandHandler {
    private BoatSimulatorController controller;
    private CommandFactory commandFactory;

    public CommandHandlerImpl(BoatSimulatorController controller) {
        this.setController(controller);
        this.commandFactory = new CommandFactory();
    }

    private void setController(BoatSimulatorController controller) {
        this.controller = controller;
    }

    @Override
    public void executeCommand(String[] args) throws DuplicateModelException, NonExistEntModelException
            , RaceAlreadyExistsException, NoSetRaceException, InsufficientContestantsException {
        String commandName = args[0];
        Executable command = this.commandFactory.getCommand(commandName, this.controller);
        command.execute(args);
    }
}
