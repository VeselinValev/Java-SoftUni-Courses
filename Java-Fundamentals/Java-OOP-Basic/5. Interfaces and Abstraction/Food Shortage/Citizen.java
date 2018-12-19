package com.company;

public class Citizen implements Buyable {
    private String name;
    private int age;
    private String id;
    private String birthDate;
    private int foodAmount = 0;

    public Citizen(String name, int age, String id, String birthDate) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.birthDate = birthDate;
    }

    @Override
    public void buyFood() {
        this.foodAmount += 10;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }
    @Override
    public int getFoodAmount() {
        return this.foodAmount;
    }
}
