package com.company;

public class Siamese extends Cat {
    private double earSize;
    private String breed;

    public Siamese(String breed, String name, double earSize) {
        super(breed, name);
        this.setEarSize(earSize);
    }


    public double getEarSize() {
        return earSize;
    }

    private void setEarSize(double earSize) {
        this.earSize = earSize;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f%n", this.getBreed(), this.getName(), this.getEarSize());
    }
}
