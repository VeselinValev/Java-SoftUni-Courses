package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.commands;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.BoatSimulatorController;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.DuplicateModelException;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.NonExistEntModelException;

public class CreateYachtCommand extends BaseCommand {

    public CreateYachtCommand(BoatSimulatorController controller) {
        super(controller);
    }

    @Override
    public void execute(String... args) throws DuplicateModelException, NonExistEntModelException {
        super.getController().createYacht(args[1], Integer.parseInt(args[2]), args[3], Integer.parseInt(args[4]));
    }
}
