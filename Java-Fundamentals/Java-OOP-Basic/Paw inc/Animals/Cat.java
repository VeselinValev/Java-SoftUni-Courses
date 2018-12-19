package Animals;

public class Cat extends Animal{
    private int intelligence;

    public Cat(String name, int age, int intelligence, String adoptionCenter) {
        super(name, age);
        this.setIntelligence(intelligence);
        super.changeAdoptionCenter(adoptionCenter);
    }

    public int getIntelligence() {
        return this.intelligence;
    }

    private void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
}