package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Person> allPersons = new ArrayList<>();
        for (String input = reader.readLine(); !input.equals("End"); input = reader.readLine()) {
            String[] tokens = input.split(" ");
            Person temp;
            if (allPersons.stream().noneMatch(x -> x.getName().equals(tokens[0]))){
                temp = new Person(tokens[0], null, null);
                allPersons.add(temp);
            }else{
                temp = allPersons.stream().filter(x -> x.getName().equals(tokens[0])).findFirst().orElse(null);
            }
            switch (tokens[1].toLowerCase()){
                case "company":
                    temp.changeCompany(new Company(tokens[2], tokens[3], Double.parseDouble(tokens[4])));
                    break;
                case "car":
                    temp.changeCar(new Car(tokens[2],Integer.parseInt( tokens[3])));
                    break;
                case "parents":
                    temp.addParent(new Parent(tokens[2], tokens[3]));
                    break;
                case "children":
                    temp.addChildren(new Children(tokens[2], tokens[3]));
                    break;
                case "pokemon":
                    temp.addPokemon(new Pokemon(tokens[2], tokens[3]));
                    break;
                default:break;
            }
        }
        String name = reader.readLine();
        Person toPrint = allPersons.stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null);
        System.out.println(toPrint.toString());
    }
}
