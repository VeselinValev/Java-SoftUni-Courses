package Exam12Nov2017;

public abstract class Cell {
    private String id;
    private int health;
    private int positionRow;
    private int positionCol;

    public Cell(String id, int health, int positionRow, int positionCol) {
        this.setId(id);
        this.setHealth(health);
        this.setPositionRow(positionRow);
        this.setPositionCol(positionCol);
    }

    private void setId(String id) {
        this.id = id;
    }

    private void setHealth(int health) {
        this.health = health;
    }

    private void setPositionRow(int positionRow) {
        this.positionRow = positionRow;
    }

    private void setPositionCol(int positionCol) {
        this.positionCol = positionCol;
    }


    public String getId() {
        return id;
    }

    public int getHealth() {
        return health;
    }

    public int getPositionRow() {
        return positionRow;
    }

    public int getPositionCol() {
        return positionCol;
    }

    public abstract int getEnergy();

    public void assimilatesCell(int health){
        this.setHealth(this.getHealth() + health);
    }

    public void reduceHealth(int damage){
        this.setHealth(this.getHealth() - damage);
    }

    public void changeCellPosition(int newRow, int newCol){
        this.setPositionRow(newRow);
        this.setPositionCol(newCol);
    }
}
