package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.commands;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.BoatSimulatorController;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.RaceAlreadyExistsException;

public class OpenRaceCommand extends BaseCommand {

    public OpenRaceCommand(BoatSimulatorController controller) {
        super(controller);
    }

    @Override
    public void execute(String... args) throws RaceAlreadyExistsException {
        super.getController().openRace(
                Integer.parseInt(args[1]),
                Integer.parseInt(args[2]),
                Integer.parseInt(args[3]),
                Boolean.parseBoolean(args[4]));
    }
}
