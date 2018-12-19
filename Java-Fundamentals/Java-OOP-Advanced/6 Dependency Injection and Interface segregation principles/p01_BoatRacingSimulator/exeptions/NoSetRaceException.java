package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions;

public class NoSetRaceException extends Exception {
    public NoSetRaceException(String message) {
        super(message);
    }
}
