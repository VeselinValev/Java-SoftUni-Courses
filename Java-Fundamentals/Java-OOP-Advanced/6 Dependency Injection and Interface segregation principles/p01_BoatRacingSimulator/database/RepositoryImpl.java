package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.database;

import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.constants.Constants;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.Modelable;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.contracts.Repository;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.DuplicateModelException;
import t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.exeptions.NonExistEntModelException;

import java.util.HashMap;
import java.util.Map;

public class RepositoryImpl<T extends Modelable> implements Repository<T> {
    private Map<String, T> itemsByModel;

    public RepositoryImpl() {
        this.itemsByModel = new HashMap<>();
    }

    @Override
    public void add(T item) throws DuplicateModelException {
        if (this.itemsByModel.containsKey(item.getModel())) {
            throw new DuplicateModelException(Constants.DUPLICATE_MODEL_MESSAGE);
        }
        this.itemsByModel.put(item.getModel(), item);
    }

    @Override
    public T getItem(String model) throws NonExistEntModelException {
        T item = this.itemsByModel.get(model);
        if (item == null) {
            throw new NonExistEntModelException(Constants.NON_EXISTENT_MODEL_MESSAGE);
        }
        return item;
    }
}
