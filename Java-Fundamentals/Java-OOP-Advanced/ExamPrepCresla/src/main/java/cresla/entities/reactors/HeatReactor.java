package cresla.entities.reactors;

import cresla.entities.containers.ModuleContainer;
import cresla.interfaces.Container;

public class HeatReactor extends AbstractReactor{
    private int heatReductionIndex;

    public HeatReactor(int id, Container container, int heatReductionIndex) {
        super(id, container);
        this.heatReductionIndex = heatReductionIndex;
    }

    @Override
    public long getTotalEnergyOutput() {
        long result =  super.getContainer().getTotalEnergyOutput();
        if (result > this.getTotalHeatAbsorbing()){
            result = 0;
        }
        return result;
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return super.getContainer().getTotalHeatAbsorbing() + this.heatReductionIndex;
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
