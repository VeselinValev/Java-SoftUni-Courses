package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.commands;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.BoatSimulatorController;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.DuplicateModelException;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.NoSetRaceException;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.NonExistEntModelException;

public class SignUpBoatCommand extends BaseCommand {

    public SignUpBoatCommand(BoatSimulatorController controller) {
        super(controller);
    }

    @Override
    public void execute(String... args) throws NonExistEntModelException, NoSetRaceException, DuplicateModelException {
        super.getController().signUpBoat(args[1]);
    }
}
