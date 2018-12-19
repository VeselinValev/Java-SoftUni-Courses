package Exam12Nov2017.Microbes;

import Exam12Nov2017.Cell;

public abstract class Microbe extends Cell {
    private int virulence;

    public Microbe(String id, int health, int positionRow, int positionCol, int virulence) {
        super(id, health, positionRow, positionCol);
        this.setVirulence(virulence);
    }

    public int getVirulence() {
        return virulence;
    }

    private void setVirulence(int virulence) {
        this.virulence = virulence;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("------Cell %s [%s,%s]%n", super.getId(), super.getPositionRow(), super.getPositionCol()));
        sb.append(String.format("--------Health: %s | Virulence: %s | Energy: %s%n", this.getHealth(), this.getVirulence(), this.getEnergy()));
        return sb.toString();
    }
}
