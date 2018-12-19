package com.company;

public class Car {
    private String model;
    private int speed;

    public Car(String model, int speed) {
        this.setModel(model);
        this.setSpeed(speed);
    }

    public String getModel() {
        return model;
    }

    private void setModel(String model) {
        this.model = model;
    }

    public int getSpeed() {
        return speed;
    }

    private void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return String.format("%s %s%n", this.getModel(), this.getSpeed());
    }
}
