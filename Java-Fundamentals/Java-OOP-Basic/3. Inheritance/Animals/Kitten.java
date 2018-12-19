package com.company;

public class Kitten extends Cat {
    public Kitten(String name, int age, String gender) {
        super(name, age, gender);
    }
    @Override
    protected String produceSound(){
        return "Miau";
    }

    @Override
    protected void setGender(String gender) {
        if (!gender.equalsIgnoreCase("female")) {
            throw new IllegalArgumentException("Invalid input!");
        }
        super.setGender(gender);
    }
}
