package com.company;

public class Rebel implements Buyable{
    private String name;
    private int age;
    private String group;
    private int foodAmount = 0;

    public Rebel(String name, int age, String group) {
        this.name = name;
        this.age = age;
        this.group = group;
    }
    @Override
    public void buyFood() {
        this.foodAmount += 5;
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
