package cresla.entities.modules;

import cresla.interfaces.EnergyModule;

public abstract class AbstractEnergyModule extends AbstractModule implements EnergyModule {
    private int energyOutput;

    AbstractEnergyModule(int id, int energyOutput) {
        super(id);
        this.energyOutput = energyOutput;
    }

    @Override
    public int getEnergyOutput() {
        return this.energyOutput;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s Module - %s%n", this.getClass().getSimpleName(), super.getId()));
        sb.append(String.format("Energy Output: %s", this.getEnergyOutput()));
        return sb.toString();
    }
}
