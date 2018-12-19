package exerciseOOP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] pizzaTokens = reader.readLine().split(" ");
        String pizzaName = pizzaTokens[1];
        String[] doughTokens = reader.readLine().split(" ");
        int toppings = Integer.parseInt(pizzaTokens[2]);
        String doughName = doughTokens[1];
        String doughType = doughTokens[2];
        Double doughWeight = Double.parseDouble(doughTokens[3]);
        try{
            if (toppings > 10){
                throw  new IllegalArgumentException("Number of toppings should be in range [0..10].");
            }
            Dough dough = new Dough(doughName, doughType, doughWeight);
            Pizza pizza = new Pizza(pizzaName, dough);
            for (int i = 0; i < toppings; i++){
                String[] ingredientsTokens = reader.readLine().split(" ");
                String toppingsType = ingredientsTokens[1];
                Double toppingWeight = Double.parseDouble(ingredientsTokens[2]);
                Toppings topping = new Toppings(toppingsType, toppingWeight);
                pizza.addToppings(topping);
            }
            System.out.println(String.format("%s - %.2f", pizzaName, pizza.calculateCalories()));
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
