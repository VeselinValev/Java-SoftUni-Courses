package cresla.entities.modules;

import cresla.interfaces.AbsorbingModule;

public abstract class AbstractAbsorberModule extends AbstractModule implements AbsorbingModule {
    private int heatAbsorbing;

    AbstractAbsorberModule(int id, int heatAbsorbing) {
        super(id);
        this.heatAbsorbing = heatAbsorbing;
    }

    @Override
    public int getHeatAbsorbing() {
        return this.heatAbsorbing;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s Module - %s%n", this.getClass().getSimpleName(), super.getId()));
        sb.append(String.format("Heat Absorbing: %s", this.getHeatAbsorbing()));
        return sb.toString();
    }
}

