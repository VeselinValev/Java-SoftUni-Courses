package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boatEngines;

public class JetEngine extends BoatEngine {

    public JetEngine(String model, int horsepower, int displacement) {
        super(model, horsepower, displacement);
        super.setCachedOutput(super.getHorsepower() * 5 + super.getDisplacement());
    }
}
