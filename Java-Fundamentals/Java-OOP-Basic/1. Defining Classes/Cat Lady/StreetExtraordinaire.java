package com.company;

public class StreetExtraordinaire extends Cat {
    private double decibelsOfMeows;
    private String breed;

    public StreetExtraordinaire(String breed, String name, double decibelsOfMeows) {
        super(breed, name);
        this.setDecibelsOfMeows(decibelsOfMeows);
    }

    public double getDecibelsOfMeows() {
        return decibelsOfMeows;
    }

    private void setDecibelsOfMeows(double decibelsOfMeows) {
        this.decibelsOfMeows = decibelsOfMeows;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f%n", this.getBreed(), this.getName(), this.getDecibelsOfMeows());
    }
}
