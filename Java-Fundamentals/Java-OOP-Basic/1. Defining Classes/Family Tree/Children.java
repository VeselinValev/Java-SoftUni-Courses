package com.company;

public class Children {
    private String name;
    private String birthDate;

    public Children(String name, String birthDate) {
        this.setName(name);
        this.setBirtDate(birthDate);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getBirtDate() {
        return birthDate;
    }

    private void setBirtDate(String birtDate) {
        this.birthDate = birtDate;
    }

    @Override
    public String toString() {
        return String.format("%s %s%n", this.getName(), this.getBirtDate());
    }
}
