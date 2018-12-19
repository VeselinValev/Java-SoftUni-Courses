package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        List<Animal> animals = new ArrayList<>();
        while(true){
            input = reader.readLine();
            if("Beast!".equalsIgnoreCase(input) || input == null){
                break;
            }
            String animalType = input;
            String[] tokens = reader.readLine().split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);
            String gender = tokens[2];
            Animal animal = null;
            try{
                switch (animalType){
                    case "Cat": animal = new Cat(name, age, gender);break;
                    case "Dog": animal = new Dog(name, age, gender);break;
                    case "Frog": animal = new Frog(name, age, gender);break;
                    case "Kitten": animal = new Kitten(name, age, "Female");break;
                    case "Tomcat": animal = new Tomcat(name, age, "Male");break;
                    default:System.out.println("Invalid input!");break;
                }
                if(animal != null){
                    animals.add(animal);
                }
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        for (Animal animal: animals){
            System.out.println(animal.toString());
        }
    }
}
