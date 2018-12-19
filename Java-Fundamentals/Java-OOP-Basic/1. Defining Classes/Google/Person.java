package com.company;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private Company company;
    private Car car;
    private List<Pokemon> pokemons;
    private List<Parent> parents;
    private List<Children> children;

    public Person(String name, Company company, Car car) {
        this.setName(name);
        this.setCompany(company);
        this.setCar(car);
        this.setPokemons(new ArrayList<>());
        this.setParents(new ArrayList<>());
        this.setChildren(new ArrayList<>());
    }

    public void changeCompany(Company company) {
        this.setCompany(company);
    }

    public void changeCar(Car car) {
        this.setCar(car);
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
    }

    public void addParent(Parent parent) {
        this.parents.add(parent);
    }

    public void addChildren(Children children) {
        this.children.add(children);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    private void setCompany(Company company) {
        this.company = company;
    }

    public Car getCar() {
        return car;
    }

    private void setCar(Car car) {
        this.car = car;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    private void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
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
        StringBuilder sb = new StringBuilder(this.getName());
        sb.append("\n").append("Company:\n");
        if (null != this.getCompany()){
            sb.append(this.getCompany().toString());
        }
        sb.append("Car:\n");
        if (null != this.getCar()){
            sb.append(this.getCar().toString());
        }
        sb.append("Pokemon:\n");
        for (Pokemon pokemon: this.getPokemons()){
            sb.append(pokemon.toString());
        }
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
