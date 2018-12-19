package cresla.entities.reactors;

import cresla.interfaces.Container;

public class CryoReactor extends AbstractReactor{
    private int cryoProductionIndex;

    public CryoReactor(int id, Container container, int cryoProductionIndex) {
        super(id, container);
        this.cryoProductionIndex = cryoProductionIndex;
    }

    @Override
    public long getTotalEnergyOutput() {
        long result =  super.getContainer().getTotalEnergyOutput() * this.cryoProductionIndex;
        if (result > this.getTotalHeatAbsorbing()){
            result = 0;
        }
        return result;
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return super.getContainer().getTotalHeatAbsorbing();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s - %s%n", this.getClass().getSimpleName(), super.getId()));
        sb.append(String.format("Energy Output: %s%n", this.getTotalEnergyOutput()));
        sb.append(String.format("Heat Absorbing: %s%n", this.getTotalHeatAbsorbing()));
        sb.append(String.format("Modules: %s", super.getModuleCount()));
        return sb.toString();
    }
}
