package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.core;

import javafx.collections.transformation.TransformationList;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.constants.Constants;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.Race;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.database.BoatSimulatorDatabase;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.*;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.factories.BoatEngineFactory;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boatEngines.BoatEngine;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boats.*;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.races.RaceImpl;

import java.util.*;
import java.util.stream.Collectors;

public class BoatSimulatorControllerImpl implements t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.BoatSimulatorController {

    private static final List<String> RANKINGS = List.of("First", "Second", "Third");

    private BoatSimulatorDatabase database;
    private Race currentRace;
    private BoatEngineFactory boatEngineFactory;
    private StringBuilder result;

    public BoatSimulatorControllerImpl(StringBuilder result) {
        this.database = new BoatSimulatorDatabase();
        this.currentRace = null;
        this.boatEngineFactory = new BoatEngineFactory();
        this.result = result;
    }

    @Override
    public void createBoatEngine(String model, String horsepower, String displacement, String engineType) throws DuplicateModelException {
        BoatEngine engine = this.boatEngineFactory.getEngine(model, horsepower, displacement, engineType);
        this.database.getEngines().add(engine);
        this.result.append("Engine model ")
                .append(model)
                .append(" with ")
                .append(horsepower)
                .append(" HP and displacement ")
                .append(displacement)
                .append(" cm3 created successfully.")
                .append(System.lineSeparator());
    }

    @Override
    public void createRowBoat(String model, int weight, int oars) throws DuplicateModelException {
        BaseBoat boat = new RowBoat(model, weight, oars);
        this.database.getBoats().add(boat);
        this.result.append("Row boat with model ")
                .append(model)
                .append(" registered successfully.")
                .append(System.lineSeparator());
    }

    @Override
    public void createSailBoat(String model, int weight, int sailEfficiency) throws DuplicateModelException {
        BaseBoat boat = new SailBoat(model, weight, sailEfficiency);
        this.database.getBoats().add(boat);
        this.result.append("Sail boat with model ")
                .append(model)
                .append(" registered successfully.")
                .append(System.lineSeparator());
    }

    @Override
    public void createPowerBoat(String model, int weight, String firstEngineModel, String secondEngineModel) throws NonExistEntModelException, DuplicateModelException {
        BoatEngine firstEngine = this.database.getEngines().getItem(firstEngineModel);
        BoatEngine secondEngine = this.database.getEngines().getItem(secondEngineModel);
        MotorBoat boat = new PowerBoat(model, weight, firstEngine, secondEngine);
        this.database.getBoats().add(boat);
        this.result.append("Power boat with model ")
                .append(model)
                .append(" registered successfully.")
                .append(System.lineSeparator());
    }

    @Override
    public void createYacht(String model, int weight, String engineModel, int cargoWeight) throws NonExistEntModelException, DuplicateModelException {
        BoatEngine engine = this.database.getEngines().getItem(engineModel);
        MotorBoat boat = new Yacht(model, weight, engine, cargoWeight);
        this.database.getBoats().add(boat);
        this.result.append("Yacht with model ")
                .append(model)
                .append(" registered successfully.")
                .append(System.lineSeparator());
    }

    @Override
    public void openRace(int distance, int windSpeed, int oceanCurrentSpeed, Boolean allowsMotorboats) throws RaceAlreadyExistsException {
        Race race = new RaceImpl(distance, windSpeed, oceanCurrentSpeed, allowsMotorboats);
        this.validateRaceIsEmpty();
        this.currentRace = race;
        this.result.append("A new race with distance ")
                .append(distance)
                .append(" meters, wind speed ")
                .append(windSpeed)
                .append(" m/s and ocean current speed ")
                .append(oceanCurrentSpeed)
                .append(" m/s has been set.")
                .append(System.lineSeparator());
    }

    @Override
    public void signUpBoat(String model) throws NonExistEntModelException, DuplicateModelException, NoSetRaceException {
        BaseBoat boat = this.database.getBoats().getItem(model);
        this.validateRaceIsSet();
        if (!this.currentRace.getAllowsMotorboats() && boat instanceof MotorBoat) {
            throw new IllegalArgumentException(Constants.INCORRECT_BOAT_TYPE_MESSAGE);
        }
        this.currentRace.addParticipant(boat);
        this.result.append("Boat with model ")
                .append(model)
                .append(" has signed up for the current Race.")
                .append(System.lineSeparator());
    }

    @Override
    public void startRace() throws InsufficientContestantsException, NoSetRaceException {
        this.validateRaceIsSet();
        Collection<BaseBoat> participants = this.currentRace.getParticipants();
        if (participants.size() < 3) {
            throw new InsufficientContestantsException(Constants.INSUFFICIENT_CONTESTANTS_MESSAGE);
        }

        Map<Double, BaseBoat> finishedByTime = new TreeMap<>();
        List<BaseBoat> notFinishedByAppearance = new ArrayList<>();


        int counter = 0;
        for (BaseBoat participant : participants) {
            double time = this.currentRace.getDistance() / participant.calculateRaceSpeed(this.currentRace);
            if (time <= 0) {
                notFinishedByAppearance.add(participant);
            } else {
                finishedByTime.put(time + counter++ * 0.000000000000001, participant);
            }
        }

        int count = 0;
        for (Map.Entry<Double, BaseBoat> pair : finishedByTime.entrySet()) {
            this.result.append(String.format("%s place: %s Model: %s Time: %s"
                    , RANKINGS.get(count)
                    , pair.getValue().getClass().getSimpleName()
                    , pair.getValue().getModel()
                    , getFinishTime(pair.getKey())))
                    .append(System.lineSeparator());
            if (++count == 3) {
                break;
            }
        }
        if (count < 3) {
            for (BaseBoat baseBoat : notFinishedByAppearance) {
                this.result.append(String.format("%s place: %s Model: %s Time: %s"
                        , RANKINGS.get(count)
                        , baseBoat.getClass().getSimpleName()
                        , baseBoat.getModel()
                        , "Did not finish!"))
                        .append(System.lineSeparator());
                if (++count == 3) {
                    break;
                }
            }
        }
        this.currentRace = null;
    }


    @Override
    public void getStatistic() {
        this.currentRace.getParticipants().stream()
                .collect(Collectors.groupingBy(BaseBoat::getClass))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(kvp -> kvp.getKey().getSimpleName()))
                .forEach(kvp -> {
                    result.append(kvp.getKey().getSimpleName())
                            .append(" -> ")
                            .append(String.format("%.2f%%"
                                    , kvp.getValue().size() * 1.0 / this.currentRace.getParticipants().size() * 100.0))
                            .append(System.lineSeparator());
                });
    }

    private String getFinishTime(Double time) {
        // ?!?!?!?!?
        if (time >= 10000000) {
            return "Did not finish!";
        }
        return String.format("%.2f sec", time);
    }

    private void validateRaceIsSet() throws NoSetRaceException {
        if (this.currentRace == null) {
            throw new NoSetRaceException(Constants.NO_SET_RACE_MESSAGE);
        }
    }

    private void validateRaceIsEmpty() throws RaceAlreadyExistsException {
        if (this.currentRace != null) {
            throw new RaceAlreadyExistsException(Constants.RACE_ALREADY_EXISTS_MESSAGE);
        }
    }
}
