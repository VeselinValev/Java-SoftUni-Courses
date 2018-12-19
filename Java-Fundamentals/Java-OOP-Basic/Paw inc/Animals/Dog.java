package Animals;

public class Dog extends Animal{
    private int amountOfCommands;


    public Dog(String name, int age, int amountOfCommands, String adoptionCenter) {
        super(name, age);
        this.setAmountOfCommands(amountOfCommands);
        super.changeAdoptionCenter(adoptionCenter);
    }

    public int getAmountOfCommands() {
        return this.amountOfCommands;
    }

    private void setAmountOfCommands(int amountOfCommands) {
        this.amountOfCommands = amountOfCommands;
    }
}
