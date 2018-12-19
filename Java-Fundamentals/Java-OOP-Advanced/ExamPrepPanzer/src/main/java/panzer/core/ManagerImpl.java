package panzer.core;

import panzer.contracts.*;
import panzer.factories.PartFactory;
import panzer.factories.VehicleFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ManagerImpl implements Manager {
    private Map<String, Vehicle> allVehicles;
    private Map<String, Vehicle> defeatedVehicles;
    private Map<String, Part> allParts;
    private BattleOperator battleOperator;

    public ManagerImpl(BattleOperator battleOperator) {
        this.allVehicles = new LinkedHashMap<>();
        this.allParts = new LinkedHashMap<>();
        this.defeatedVehicles = new LinkedHashMap<>();
        this.battleOperator = battleOperator;
    }

    @Override
    public String addVehicle(List<String> arguments) {
        this.allVehicles.put(arguments.get(2), VehicleFactory.createVehicle(arguments));
        return String.format("Created %s Vehicle - %s", arguments.get(1), arguments.get(2));
    }

    @Override
    public String addPart(List<String> arguments) {
        if (arguments.get(2).equals("Arsenal")) {
            this.allVehicles.get(arguments.get(1)).addArsenalPart(PartFactory.createArsenalPart(arguments));
            this.allParts.put(arguments.get(3), PartFactory.createArsenalPart(arguments));
        } else if (arguments.get(2).equals("Shell")) {
            this.allVehicles.get(arguments.get(1)).addShellPart(PartFactory.createShellPart(arguments));
            this.allParts.put(arguments.get(3), PartFactory.createShellPart(arguments));
        } else {
            this.allVehicles.get(arguments.get(1)).addEndurancePart(PartFactory.createEndurancePart(arguments));
            this.allParts.put(arguments.get(3), PartFactory.createEndurancePart(arguments));
        }
        return String.format("Added %s - %s to Vehicle - %s", arguments.get(2), arguments.get(3), arguments.get(1));
    }

    @Override
    public String inspect(List<String> arguments) {
        if (this.allVehicles.containsKey(arguments.get(1))) {
            return this.allVehicles.get(arguments.get(1)).toString();
        } else {
            return this.allParts.get(arguments.get(1)).toString();
        }
    }

    @Override
    public String battle(List<String> arguments) {
        Vehicle vehicleOne = this.allVehicles.get(arguments.get(1));
        Vehicle vehicleTwo = this.allVehicles.get(arguments.get(2));
        String winnerModel = this.battleOperator.battle(vehicleOne, vehicleTwo);
        String loserModel = winnerModel.equals(vehicleOne.getModel()) ? vehicleTwo.getModel() : vehicleOne.getModel();
        this.defeatedVehicles.put(loserModel, this.allVehicles.get(loserModel));
        this.allVehicles.remove(loserModel);

        return String.format("%s versus %s -> %s Wins! Flawless Victory!",
                arguments.get(1), arguments.get(2), winnerModel);
    }

    @Override
    public String terminate(List<String> arguments) {
        StringBuilder sb = new StringBuilder();
        sb.append("Remaining Vehicles: ");
        String remainingVehicleModels = this.allVehicles.isEmpty() ? "None" :
                String.join(", ", this.allVehicles.values().stream().map(Modelable::getModel).collect(Collectors.toList()));
        sb.append(remainingVehicleModels).append(System.lineSeparator());
        sb.append("Defeated Vehicles: ");
        String defeatedVehicleModels = this.defeatedVehicles.isEmpty() ? "None" :
                String.join(", ", this.defeatedVehicles.values().stream().map(Modelable::getModel).collect(Collectors.toList()));
        sb.append(defeatedVehicleModels).append(System.lineSeparator());
        final int[] partCount = {0};
        this.allVehicles.values().forEach(x -> {
            try {
                x.getParts().forEach(y -> partCount[0]++);
            } catch (NoSuchMethodException | NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        sb.append(String.format("Currently Used Parts: %s", String.valueOf(partCount[0])));
        return sb.toString();
    }
}
