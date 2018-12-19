package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.commands;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.BoatSimulatorController;

public class GetStatisticCommand extends BaseCommand {

    public GetStatisticCommand(BoatSimulatorController controller) {
        super(controller);
    }

    @Override
    public void execute(String... args) {
        super.getController().getStatistic();
    }
}
