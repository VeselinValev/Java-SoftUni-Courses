package core.commands;

import contracts.Executable;
import contracts.Inject;
import contracts.Repository;
import contracts.UnitFactory;

public abstract class Command implements Executable {
    @Inject
    private String[] data;
    @Inject
    private Repository repository;
    @Inject
    private UnitFactory unitFactory;


    protected String[] getData() {
        return this.data;
    }

    protected Repository getRepository() {
        return this.repository;
    }

    protected UnitFactory getUnitFactory() {
        return this.unitFactory;
    }
}
