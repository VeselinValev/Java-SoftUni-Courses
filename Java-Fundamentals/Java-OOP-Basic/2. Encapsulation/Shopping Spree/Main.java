package exerciseOOP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Person> persons = new LinkedHashMap();
        Map<String, Product> products = new LinkedHashMap();
        String[] inputPersons = reader.readLine().split(";");
        String[] inputProducts = reader.readLine().split(";");
        try {
            for (int i = 0; i < inputPersons.length; i++) {
                Person person = new Person(inputPersons[i].split("=")[0], Double.parseDouble(inputPersons[i].split("=")[1]));
                persons.put(person.getName(), person);
            }
            for (int i = 0; i < inputProducts.length; i++) {
                Product product = new Product(inputProducts[i].split("=")[0], Double.parseDouble(inputProducts[i].split("=")[1]));
                products.put(product.getName(), product);
            }
            String commands;
            while (true) {
                commands = reader.readLine();
                if (commands.equals("END")) {
                    break;
                }
                String nameOfPerson = commands.split("\\s+")[0];
                String nameOfProduct = commands.split("\\s+")[1];
                if (persons.containsKey(nameOfPerson) && products.containsKey(nameOfProduct)) {
                    Person person = persons.get(nameOfPerson);
                    Product product = products.get(nameOfProduct);
                    if (person.hasEnoughMoney(product.getCost())) {
                        person.buyProduct(product);
                        System.out.println(String.format("%s bought %s", person.getName(), product.getName()));
                    } else {
                        System.out.println(String.format("%s can't afford %s", person.getName(), product.getName()));
                    }
                }
            }

            for (Person person : persons.values()) {
                if (person.getProductList().isEmpty()) {
                    System.out.println(String.format("%s - Nothing bought", person.getName()));
                } else {
                    StringBuilder builder = new StringBuilder();
                    builder.append(person.getProductList().get(0).getName());
                    for (int i = 1; i < person.getProductList().size(); i++){
                        builder.append(String.format(", %s", person.getProductList().get(i).getName()));
                    }
                    System.out.println(String.format("%s - %s", person.getName(), builder));
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
