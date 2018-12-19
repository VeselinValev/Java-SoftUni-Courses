package cresla.entities.reactors;

import cresla.entities.containers.ModuleContainer;
import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Container;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Reactor;

import java.lang.reflect.Field;
import java.util.LinkedList;

public abstract class AbstractReactor implements Reactor {
    private int id;
    private Container container;

    AbstractReactor(int id, Container container) {
        this.id = id;
        this.container = container;
    }

    Container getContainer() {
        return this.container;
    }

    @Override
    public abstract long getTotalEnergyOutput();

    @Override
    public abstract long getTotalHeatAbsorbing();

    @Override
    public int getModuleCount() {
        Class<?> container = ModuleContainer.class;
        Field field = container.getDeclaredFields()[1];
        field.setAccessible(true);
        try {
            return ((LinkedList) field.get(this.container)).size();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void addEnergyModule(EnergyModule energyModule) {
        this.container.addEnergyModule(energyModule);
    }

    @Override
    public void addAbsorbingModule(AbsorbingModule absorbingModule) {
        this.container.addAbsorbingModule(absorbingModule);
    }

    @Override
    public int getId() {
        return this.id;
    }

}
