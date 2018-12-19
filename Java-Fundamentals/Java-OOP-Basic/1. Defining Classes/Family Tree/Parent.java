package com.company;

public class Parent {
    private String name;
    private String birthDate;

    public Parent(String name, String birthDate) {
        this.setName(name);
        this.setBirthDate(birthDate);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    private void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format("%s %s%n", this.getName(), this.getBirthDate());
    }
}
