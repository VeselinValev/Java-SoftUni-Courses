package Cars;

public class ShowCar extends Car{
    private int stars;

    public ShowCar(String brand, String model, int yearOfProduction, int horsepower, int acceleration, int suspension, int durability) {
        super(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
        this.setStars(0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s %s%n", super.getBrand(), super.getModel(), super.getYearOfProduction()));
        sb.append(String.format("%s HP, 100 m/h in %s s%n", super.getHorsepower(), super.getAcceleration()));
        sb.append(String.format("%s Suspension force, %s Durability%n", super.getSuspension(), super.getDurability()));
        sb.append(String.format("%s *", this.getStars()));
        return sb.toString();
    }

    public int getStars() {
        return this.stars;
    }

    private void setStars(int stars) {
        this.stars = stars;
    }

    public void increaseStars(int index){
        setStars(this.getStars() + index);
    }
}
