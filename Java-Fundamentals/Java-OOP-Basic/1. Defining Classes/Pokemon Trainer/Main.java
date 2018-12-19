package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Trainer> allTrainers = new ArrayList<>();
        for (String input = reader.readLine(); !input.equals("Tournament"); input = reader.readLine()){
            String[] tokens = input.split(" ");
            Pokemon pokemon = new Pokemon(tokens[1], tokens[2], Integer.parseInt(tokens[3]));
            if (allTrainers.stream().noneMatch(x -> x.getName().equals(tokens[0]))){
                allTrainers.add(new Trainer(tokens[0]));
            }
            Trainer temp = allTrainers.stream().filter(x -> x.getName().equals(tokens[0])).findFirst().orElse(null);
            temp.addPokemon(pokemon);
        }
        for (String input = reader.readLine(); !input.equals("End"); input = reader.readLine()){
            String element = input;
            for (Trainer trainer: allTrainers){
                if (trainer.getPokemons().stream().anyMatch(x -> x.getElement().equals(element))){
                    trainer.addBadge();
                }else{
                    trainer.getPokemons().forEach(Pokemon::reduceHealth);
                    trainer.getPokemons().removeIf(x -> x.getHealth() <= 0);
                }
            }
        }
        allTrainers.stream()
                .sorted((x,y) -> Integer.compare(y.getBadges(), x.getBadges()))
                .forEach(x -> System.out.printf("%s %s %s%n", x.getName(), x.getBadges(), x.getPokemons().size()));
    }
}
