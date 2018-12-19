package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.database;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.Repository;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boatEngines.BoatEngine;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boats.BaseBoat;

public class BoatSimulatorDatabase {
    Repository<BaseBoat> boats;
    Repository<BoatEngine> engines;

    public BoatSimulatorDatabase() {
        this.setBoats(new RepositoryImpl<>());
        this.setEngines(new RepositoryImpl<>());
    }

    public Repository<BaseBoat> getBoats() {
        return this.boats;
    }

    private void setBoats(Repository<BaseBoat> boats) {
        this.boats = boats;
    }

    public Repository<BoatEngine> getEngines() {
        return this.engines;
    }

    private void setEngines(Repository<BoatEngine> engines) {
        this.engines = engines;
    }
}
