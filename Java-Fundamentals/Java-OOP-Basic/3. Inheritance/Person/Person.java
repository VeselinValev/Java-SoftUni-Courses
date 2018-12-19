package exerciseOOP;

public class Person {
    private String name;
    private int  age;

    public Person(String name, int  age) {
        this.setName(name);
        this.setAge(age);
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        if(name.length() < 3){
            throw new IllegalArgumentException("Name's length should not be less than 3 symbols!");
        }
        this.name = name;
    }

    public int  getAge() {
        return age;
    }

    protected void setAge(int age) {
        if(age < 0) {
            throw new IllegalArgumentException("Age must be positive!");
        }
        this.age = age;
    }
    @Override
    public String toString(){
        final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.format("Name: %s, Age: %s", this.getName(), this.getAge()));
            return stringBuilder.toString();
    }
}
