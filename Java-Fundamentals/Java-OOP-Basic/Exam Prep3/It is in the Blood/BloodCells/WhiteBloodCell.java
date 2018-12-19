package Exam12Nov2017.BloodCells;

public class WhiteBloodCell extends BloodCell {
    private int size;

    public WhiteBloodCell(String id, int health, int positionRow, int positionCol, int size) {
        super(id, health, positionRow, positionCol);
        this.setSize(size);
    }

    public int getSize() {
        return size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("------Cell %s [%s,%s]%n", super.getId(), super.getPositionRow(), super.getPositionCol()));
        sb.append(String.format("--------Health: %s | Size: %s | Energy: %s%n", super.getHealth(), this.getSize(), this.getEnergy()));
        return sb.toString();
    }

    @Override
    public int getEnergy() {
        return (this.getHealth() + this.getSize())* 2;
    }
}
