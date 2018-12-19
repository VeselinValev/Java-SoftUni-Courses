package exerciseOOP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        List<Animal> animals = new ArrayList<>();
        while (true) {
            if ("End".equalsIgnoreCase(input = reader.readLine())) {
                break;
            }

            String[] animalTokens = input.split("\\s+");
            Animal animal = createAnimal(animalTokens);

            String[] foodTokens = reader.readLine().split("\\s+");
            Food food = createFood(foodTokens);

            if (animal != null) {
                System.out.println(animal.makeSound());
                if (food != null) {
                    try {
                        animal.eat(food);
                    } catch (IllegalArgumentException iae) {
                        System.out.println(iae.getMessage());
                    }
                }
                animals.add(animal);
            }
        }
        for (Animal animal: animals) {
            System.out.println(animal);
        }
    }
    public static Animal createAnimal(String[] animalTokens) {
        Animal animal = null;
        switch (animalTokens[0]) {
            case "Cat":
                animal = new Cat(animalTokens[1], animalTokens[0], Double.parseDouble(animalTokens[2]),0 ,
                        animalTokens[3], animalTokens[4]);
                break;
            case "Tiger":
                animal = new Tiger(animalTokens[1], animalTokens[0], Double.parseDouble(animalTokens[2]), 0 ,
                        animalTokens[3]);
                break;
            case "Mouse":
                animal = new Mouse(animalTokens[1], animalTokens[0], Double.parseDouble(animalTokens[2]), 0 ,
                        animalTokens[3]);
                break;
            case "Zebra":
                animal = new Zebra(animalTokens[1], animalTokens[0], Double.parseDouble(animalTokens[2]), 0 ,
                        animalTokens[3]);
                break;
            default:return null;

        }
        return animal;
    }

    public static Food createFood(String[] foodTokens) {
        Food food = null;
        switch (foodTokens[0]) {
            case "Vegetable":
                food = new Vegetable(Integer.parseInt(foodTokens[1]));
                break;
            case "Meat":
                food = new Meat(Integer.parseInt(foodTokens[1]));
                break;
            default:return null;
        }
        return food;
    }
}
