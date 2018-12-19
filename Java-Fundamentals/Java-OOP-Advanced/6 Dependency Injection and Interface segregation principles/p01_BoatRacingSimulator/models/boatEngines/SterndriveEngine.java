package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boatEngines;

public class SterndriveEngine extends BoatEngine {

    public SterndriveEngine(String model, int horsepower, int displacement) {
        super(model, horsepower, displacement);
        super.setCachedOutput(super.getHorsepower() * 7 + super.getDisplacement());
    }
}
