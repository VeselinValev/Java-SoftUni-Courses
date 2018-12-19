package Cars;

import java.util.ArrayList;
import java.util.List;

public class PerformanceCar extends Car {
    private List<String> addOns;

    public PerformanceCar(String brand, String model, int yearOfProduction, int horsepower, int acceleration, int suspension, int durability) {
        super(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
        this.setAddOns(new ArrayList<>());
        this.setHorsepower(horsepower);
        this.setSuspension(suspension);
        this.setAddOns(new ArrayList<>());
    }

    @Override
    protected void setHorsepower(int horsepower) {
        super.setHorsepower(horsepower + (horsepower * 50)/100);
    }

    @Override
    protected void setSuspension(int suspension) {
        super.setSuspension(suspension - (suspension * 25)/100);
    }

    public List<String> getAddOns() {
        return this.addOns;
    }

    public void setAddOns(List<String> addOns) {
        this.addOns = addOns;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s %s%n", super.getBrand(), super.getModel(), super.getYearOfProduction()));
        sb.append(String.format("%s HP, 100 m/h in %s s%n", super.getHorsepower(), super.getAcceleration()));
        sb.append(String.format("%s Suspension force, %s Durability%n", super.getSuspension(), super.getDurability()));
        sb.append("Add-ons: ");
        if (this.getAddOns().isEmpty()){
            sb.append("None");
        }else{
            sb.append(this.getAddOns().toString().replaceAll("[\\[\\]]", ""));
        }
        return sb.toString();
    }
}
