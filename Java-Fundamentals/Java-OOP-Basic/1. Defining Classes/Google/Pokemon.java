package com.company;

public class Pokemon {
    private String name;
    private String type;

    public Pokemon(String name, String type) {
        this.setName(name);
        this.setType(type);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    private void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s %s%n", this.getName(), this.getType());
    }
}
