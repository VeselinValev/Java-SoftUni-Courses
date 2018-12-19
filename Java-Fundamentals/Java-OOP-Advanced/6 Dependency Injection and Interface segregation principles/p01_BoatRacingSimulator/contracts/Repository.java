package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.DuplicateModelException;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.NonExistEntModelException;

public interface Repository<T extends Modelable> {
    void add(T item) throws DuplicateModelException;

    T getItem(String model) throws NonExistEntModelException;
}
