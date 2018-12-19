package com.company;

public abstract class Cat {
    private String name;
    private String breed;

    public Cat(String breed, String name) {
        this.setName(name);
        this.setBreed(breed);
    }

    public String getBreed() {
        return breed;
    }

    private void setBreed(String breed) {
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
