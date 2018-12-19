package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.*;

public interface BoatSimulatorController {
    void createBoatEngine(String model, String horsepower, String displacement, String engineType) throws DuplicateModelException;

    void createRowBoat(String model, int weight, int oars) throws DuplicateModelException;

    void createSailBoat(String model, int weight, int sailEfficiency) throws DuplicateModelException;

    void createPowerBoat(String model, int weight, String firstEngineModel, String secondEngineModel) throws NonExistEntModelException, DuplicateModelException;

    void createYacht(String model, int weight, String engineModel, int cargoWeight) throws NonExistEntModelException, DuplicateModelException;

    void openRace(int distance, int windSpeed, int oceanCurrentSpeed, Boolean allowsMotorboats) throws RaceAlreadyExistsException;

    void signUpBoat(String model) throws NonExistEntModelException, DuplicateModelException, NoSetRaceException;

    void startRace() throws InsufficientContestantsException, NoSetRaceException;

    void getStatistic();
}
