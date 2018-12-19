package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Engine> allEngines = new ArrayList<>();
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++){
            String[] input = reader.readLine().split(" ");
            Engine engine;
            if (input.length == 4){
                engine = new Engine(input[0], Integer.parseInt(input[1]), input[2], input[3]);
            }else if (input.length == 2){
                engine = new Engine(input[0], Integer.parseInt(input[1]), "n/a", "n/a");
            }else {
                try{
                    int test = Integer.parseInt(input[2]);
                    engine = new Engine(input[0], Integer.parseInt(input[1]), input[2], "n/a");
                }catch (NumberFormatException e){
                    engine = new Engine(input[0], Integer.parseInt(input[1]), "n/a", input[2]);
                }
            }
            allEngines.add(engine);
        }
        int m = Integer.parseInt(reader.readLine());
        for (int i = 0; i < m; i++){
            String[] input = reader.readLine().split(" ");
            Car car;
            Engine temp = allEngines.stream().filter(x -> x.getModel().equals(input[1])).findFirst().orElse(null);
            if (input.length == 4){
                car = new Car(input[0], temp, input[2], input[3]);
            }else if (input.length == 2){
                car = new Car(input[0], temp, "n/a", "n/a");
            }else {
                try{
                    int test = Integer.parseInt(input[2]);
                    car = new Car(input[0], temp, input[2], "n/a");
                }catch (NumberFormatException e){
                    car = new Car(input[0], temp, "n/a", input[2]);
                }
            }
            StringBuilder sb = new StringBuilder(car.getModel());
            sb.append(":\n")
                    .append(String.format("  %s:%n", car.getEngine().getModel()))
                    .append(String.format("    Power: %s%n", car.getEngine().getPower()))
                    .append(String.format("    Displacement: %s%n", car.getEngine().getDisplacement()))
                    .append(String.format("    Efficiency: %s%n", car.getEngine().getEfficiency()))
                    .append(String.format("  Weight: %s%n", car.getWeight()))
                    .append(String.format("  Color: %s", car.getColor()));
            System.out.println(sb);
        }
    }
}
