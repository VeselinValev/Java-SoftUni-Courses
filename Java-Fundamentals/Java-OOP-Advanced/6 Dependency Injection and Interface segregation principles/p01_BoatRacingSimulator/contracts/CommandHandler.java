package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.*;

public interface CommandHandler {
    void executeCommand(String[] args) throws DuplicateModelException
            , NonExistEntModelException, RaceAlreadyExistsException
            , NoSetRaceException, InsufficientContestantsException;
}
