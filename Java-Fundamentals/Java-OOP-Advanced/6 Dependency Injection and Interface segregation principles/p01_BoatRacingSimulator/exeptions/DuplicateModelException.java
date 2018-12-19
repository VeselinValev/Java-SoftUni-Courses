package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions;

public class DuplicateModelException extends Exception {
    public DuplicateModelException(String message) {
        super(message);
    }
}
