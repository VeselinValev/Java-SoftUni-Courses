package Cars;

public abstract class Car {
    private String brand;
    private String model;
    private int yearOfProduction;
    private int horsepower;
    private int acceleration;
    private int suspension;
    private int durability;

    public Car(String brand, String model, int yearOfProduction, int horsepower, int acceleration, int suspension, int durability) {
        this.brand = brand;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.horsepower = horsepower;
        this.acceleration = acceleration;
        this.suspension = suspension;
        this.durability = durability;
    }

    private void setBrand(String brand) {
        this.brand = brand;
    }

    private void setModel(String model) {
        this.model = model;
    }

    private void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    protected void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    private void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    protected void setSuspension(int suspension) {
        this.suspension = suspension;
    }

    private void setDurability(int durability) {
        this.durability = durability;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getModel() {
        return this.model;
    }

    public int getYearOfProduction() {
        return this.yearOfProduction;
    }

    public int getHorsepower() {
        return this.horsepower;
    }

    public int getAcceleration() {
        return this.acceleration;
    }

    public int getSuspension() {
        return this.suspension;
    }

    public int getDurability() {
        return this.durability;
    }

    public void tuneCar(int index){
        this.horsepower = this.getHorsepower() + index;
        this.suspension = this.getSuspension() + index / 2;
    }

    public void decreaseDurability(int length){
        this.setDurability(this.getDurability() - length * length);
    }

    @Override
    public abstract String toString();
}
