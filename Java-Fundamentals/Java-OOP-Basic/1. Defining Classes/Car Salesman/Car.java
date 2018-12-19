package com.company;

public class Car {
    private String model;
    private Engine engine;
    private String weight;
    private String color;

    public Car(String model, Engine engine, String weight, String color) {
        this.setModel(model);
        this.setEngine(engine);
        this.setWeight(weight);
        this.setColor(color);
    }

    public String getModel() {
        return model;
    }

    private void setModel(String model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    private void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getWeight() {
        return weight;
    }

    private void setWeight(String weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    private void setColor(String color) {
        this.color = color;
    }
}
