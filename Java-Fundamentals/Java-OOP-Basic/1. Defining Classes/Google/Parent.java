package com.company;

public class Parent {
    private String name;
    private String birthday;

    public Parent(String name, String birthday) {
        this.setName(name);
        this.setBirthday(birthday);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    private void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return String.format("%s %s%n", this.getName(), this.getBirthday());
    }
}
