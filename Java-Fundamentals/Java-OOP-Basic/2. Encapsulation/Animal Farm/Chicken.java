package exerciseOOP;

public class Chicken {
    public int getAge() {
        return age;
    }

    private int age;
    private String name;

    public String getName() {
        return name;
    }

    public Chicken(int age, String name) {
        this.setAge(age);
        this.setName(name);
    }

    private void setAge(int age) {
        if(age <0 || age > 15){
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        }
        this.age = age;
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    public double calculateProductPerDay(int age) {
        double eggsPerDay = 0;
        if(age < 6){
            eggsPerDay = 2;
        }else if(age >= 6 && age < 12){
            eggsPerDay = 1;
        }else{
            eggsPerDay = 0.75;
        }
        return eggsPerDay;
    }
}
