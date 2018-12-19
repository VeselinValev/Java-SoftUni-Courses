package exerciseOOP;

public class Dough {
    private String name;
    private String type;
    private double weight;
    private double modifier;

    public Dough(String name, String type, double weight) {
        this.setName(name);
        this.setType(type);
        this.setWeight(weight);
        this.setModifier(this.name, this.type);
    }

    public double getModifier() {
        return modifier;
    }

    public void setModifier(String name, String type) {
        double mod1 = name.equals("Wholegrain")? 1 : 1.5;
        double mod2 = type.equals("Crispy")? 0.9 : (type.equals("Chewy")? 1.1 : 1);
        this.modifier = mod1 * mod2;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if(name.equals("Wholegrain") || name.equals("White")){
            this.name = name;
        }else{
            throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    public String getType() {
        return type;
    }

    private void setType(String type) {
        if(type.equals("Crispy") || type.equals("Chewy")|| type.equals("Homemade")){
            this.type = type;
        }else{
            throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    public double getWeight() {
        return weight;
    }

    private void setWeight(double weight) {
        if(weight < 1 || weight >200){
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }
}
