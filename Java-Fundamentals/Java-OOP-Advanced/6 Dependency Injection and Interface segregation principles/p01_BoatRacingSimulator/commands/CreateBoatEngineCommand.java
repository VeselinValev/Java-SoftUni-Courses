package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.commands;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.BoatSimulatorController;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.DuplicateModelException;

public class CreateBoatEngineCommand extends BaseCommand {

    public CreateBoatEngineCommand(BoatSimulatorController controller) {
        super(controller);
    }

    @Override
    public void execute(String... args) throws DuplicateModelException {
        super.getController().createBoatEngine(args[1], args[2], args[3], args[4]);
    }
}
