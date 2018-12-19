package com.company;

public class Pokemon {
    private String name;
    private String element;
    private int health;

    public Pokemon(String name, String element, int health) {
        this.setName(name);
        this.setElement(element);
        this.setHealth(health);
    }

    public void reduceHealth(){
        this.setHealth(this.getHealth() - 10);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getElement() {
        return element;
    }

    private void setElement(String element) {
        this.element = element;
    }

    public int getHealth() {
        return health;
    }

    private void setHealth(int health) {
        this.health = health;
    }
}
