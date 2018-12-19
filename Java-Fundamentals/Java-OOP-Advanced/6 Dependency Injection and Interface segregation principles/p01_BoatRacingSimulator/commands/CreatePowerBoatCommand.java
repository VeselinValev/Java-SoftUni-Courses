package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.commands;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.BoatSimulatorController;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.DuplicateModelException;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.NonExistEntModelException;

public class CreatePowerBoatCommand extends BaseCommand {

    public CreatePowerBoatCommand(BoatSimulatorController controller) {
        super(controller);
    }

    @Override
    public void execute(String... args) throws DuplicateModelException, NonExistEntModelException {
        super.getController().createPowerBoat(args[1], Integer.parseInt(args[2]), args[3], args[4]);
    }
}
