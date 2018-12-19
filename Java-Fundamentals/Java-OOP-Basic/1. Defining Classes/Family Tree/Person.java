package com.company;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private String birthDate;
    private List<Parent> parents;
    private List<Children> children;

    public Person(String name, String birthDate) {
        this.setName(name);
        this.setBirthDate(birthDate);
        this.setChildren(new ArrayList<>());
        this.setParents(new ArrayList<>());
    }

    public void addChildren(Children children){
        this.children.add(children);
    }

    public void addParents(Parent parent){
        this.parents.add(parent);
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

    public List<Parent> getParents() {
        return parents;
    }

    private void setParents(List<Parent> parents) {
        this.parents = parents;
    }

    public List<Children> getChildren() {
        return children;
    }

    private void setChildren(List<Children> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName()).append(" ").append(this.getBirthDate()).append("\n");
        sb.append("Parents:\n");
        for (Parent parent: this.getParents()){
            sb.append(parent.toString());
        }
        sb.append("Children:\n");
        for (Children children: this.getChildren()){
            sb.append(children.toString());
        }
        return sb.toString();
    }
}
