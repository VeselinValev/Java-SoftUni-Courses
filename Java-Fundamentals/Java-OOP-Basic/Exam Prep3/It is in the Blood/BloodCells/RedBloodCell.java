package Exam12Nov2017.BloodCells;

public class RedBloodCell extends BloodCell {
    private int velocity;

    public RedBloodCell(String id, int health, int positionRow, int positionCol, int velocity) {
        super(id, health, positionRow, positionCol);
        this.setVelocity(velocity);
    }

    public int getVelocity() {
        return velocity;
    }

    private void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("------Cell %s [%s,%s]%n", super.getId(), super.getPositionRow(), super.getPositionCol()));
        sb.append(String.format("--------Health: %s | Velocity: %s | Energy: %s%n", super.getHealth(), this.getVelocity(), this.getEnergy()));
        return sb.toString();
    }

    @Override
    public int getEnergy() {
        return this.getHealth() + this.getVelocity();
    }
}
