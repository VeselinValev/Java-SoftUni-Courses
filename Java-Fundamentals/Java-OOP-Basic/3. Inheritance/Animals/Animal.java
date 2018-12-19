package com.company;

public abstract class Animal {
    private String name;
    private int age;
    private String gender;

    public Animal(String name, int age, String gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid input!");
        }
        this.name = name.trim();
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        if(age < 1){
            throw new IllegalArgumentException("Invalid input!");
        }
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    protected void setGender(String gender) {
        if(gender==null || gender.trim().isEmpty() || (!gender.equalsIgnoreCase("female") && !gender.equalsIgnoreCase("male"))){
            throw new IllegalArgumentException("Invalid input!");
        }
        this.gender = gender.trim();
    }

    protected String produceSound(){
        return "Not implemented!";
    }

    @Override
    public String toString() {
        return String.format("%s\n%s %d %s\n%s", this.getClass().getSimpleName(),
                this.getName(), this.getAge(), this.getGender(), this.produceSound());
    }
}
