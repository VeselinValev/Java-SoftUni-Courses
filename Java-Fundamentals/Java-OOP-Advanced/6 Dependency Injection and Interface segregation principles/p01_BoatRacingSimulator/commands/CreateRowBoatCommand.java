package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.commands;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.BoatSimulatorController;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.DuplicateModelException;

public class CreateRowBoatCommand extends BaseCommand {

    public CreateRowBoatCommand(BoatSimulatorController controller) {
        super(controller);
    }

    @Override
    public void execute(String... args) throws DuplicateModelException {
        super.getController().createRowBoat(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
    }
}
