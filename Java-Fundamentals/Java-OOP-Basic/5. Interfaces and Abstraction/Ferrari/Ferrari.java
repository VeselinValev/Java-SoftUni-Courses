package com.company;

public class Ferrari implements Car {
    public final String MODEL = "488-Spider";
    private String driver;

    public Ferrari(String driver) {
        this.driver = driver;
    }

    @Override
    public String getDriverName() {
        return this.driver;
    }

    @Override
    public String gasPedal() {
        return "Zadu6avam sA!";
    }

    @Override
    public String brakes() {
        return "Brakes!";
    }

    @Override
    public String toString(){
        return String.format("%s/%s/%s/%s%n", MODEL, brakes(), gasPedal(), getDriverName());
    }
}
