package com.company;

public class Cymric extends Cat {
    private double furLength;
    private String breed;

    public Cymric(String breed, String name, double furLength) {
        super(breed, name);
        this.setFurLength(furLength);
    }

    public double getFurLength() {
        return furLength;
    }

    private void setFurLength(double furLength) {
        this.furLength = furLength;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f%n", this.getBreed(), this.getName(), this.getFurLength());
    }
}
