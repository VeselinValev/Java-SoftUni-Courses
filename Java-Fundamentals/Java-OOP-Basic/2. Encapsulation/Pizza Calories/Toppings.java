package exerciseOOP;

public class Toppings {
    private String type;
    private double weight;
    private double modifier;

    public Toppings(String type, double weight) {
        this.setType(type);
        this.setWeight(weight);
        this.setModifier(this.type);
    }

    public double getModifier() {
        return modifier;
    }

    public void setModifier(String type) {
        switch (type){
            case "Meat": this.modifier = 1.2;break;
            case "Veggies": this.modifier = 0.8;break;
            case "Cheese": this.modifier = 1.1;break;
            case "Sauce": this.modifier = 0.9;break;
        }
    }
    public String getType() {
        return type;
    }

    private void setType(String type) {
        if(type.equals("Meat") || type.equals("Veggies")|| type.equals("Cheese")|| type.equals("Sauce")){
            this.type = type;
        }else{
            throw new IllegalArgumentException(String.format("Cannot place %s on top of your pizza.", type));
        }
    }

    public double getWeight() {
        return weight;
    }

    private void setWeight(double weight) {
        if(weight < 1 || weight >50){
            throw new IllegalArgumentException(String.format("%s weight should be in the range [1..50].", this.type));
        }
        this.weight = weight;
    }
}
