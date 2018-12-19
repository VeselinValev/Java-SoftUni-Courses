package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.DuplicateModelException;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.models.boats.BaseBoat;

import java.util.Collection;
import java.util.List;

public interface Race {
    int getDistance();

    int getWindSpeed();

    int getOceanCurrentSpeed();

    Boolean getAllowsMotorboats();

    void addParticipant(BaseBoat boat) throws DuplicateModelException;

    Collection<BaseBoat> getParticipants();
}
