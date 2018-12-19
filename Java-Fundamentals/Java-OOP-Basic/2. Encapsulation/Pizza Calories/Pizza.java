package exerciseOOP;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Toppings> toppings;

    public Pizza(String name, Dough dough) {
        this.setName(name);
        this.setDough(dough);
        this.toppings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if(name.trim().isEmpty() || name.length() > 15){
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
        this.name = name;
    }

    public Dough getDough() {
        return dough;
    }

    private void setDough(Dough dough) {
        this.dough = dough;
    }

    public List<Toppings> getToppings() {
        return toppings;
    }

    public void addToppings(Toppings topping) {
        this.toppings.add(topping);
    }
    public double calculateCalories(){
        double doughCalories = dough.getWeight()*dough.getModifier()*2;
        double toppingsCalories = 0.0;
        for (int i = 0; i < toppings.size(); i++){
            toppingsCalories += toppings.get(i).getWeight() * toppings.get(i).getModifier()*2;
        }
        return doughCalories + toppingsCalories;
    }
}
