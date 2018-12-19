package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions;

public class NonExistEntModelException extends Exception {
    public NonExistEntModelException(String message) {
        super(message);
    }
}
