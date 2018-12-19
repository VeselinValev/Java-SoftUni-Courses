package com.company;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private String name;
    private int badges;
    private List<Pokemon> pokemons;

    public Trainer(String name) {
        this.setName(name);
        this.setBadges(0);
        this.setPokemons(new ArrayList<>());
    }

    public void addBadge(){
        this.setBadges(this.getBadges() + 1);
    }

    public void addPokemon(Pokemon pokemon){
        this.pokemons.add(pokemon);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getBadges() {
        return badges;
    }

    private void setBadges(int badges) {
        this.badges = badges;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    private void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}
