package com.company;

public class Engine {
    private String model;
    private int power;
    private String displacement;
    private String efficiency;

    public Engine(String model, int power, String displacement, String efficiency) {
        this.setModel(model);
        this.setPower(power);
        this.setDisplacement(displacement);
        this.setEfficiency(efficiency);
    }

    public String getModel() {
        return model;
    }

    private void setModel(String model) {
        this.model = model;
    }

    public int getPower() {
        return power;
    }

    private void setPower(int power) {
        this.power = power;
    }

    public String  getDisplacement() {
        return displacement;
    }

    private void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public String getEfficiency() {
        return efficiency;
    }

    private void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }
}
