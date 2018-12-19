package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.commands;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.BoatSimulatorController;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.DuplicateModelException;

public class CreateSailBoatCommand extends BaseCommand {

    public CreateSailBoatCommand(BoatSimulatorController controller) {
        super(controller);
    }

    @Override
    public void execute(String... args) throws DuplicateModelException {
        super.getController().createSailBoat(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
    }
}
