package exerciseOOP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.instrument.IllegalClassFormatException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] carTokens = reader.readLine().split(" ");
        String[] truckTokens = reader.readLine().split(" ");
        int numberOfCommands = Integer.parseInt(reader.readLine());
        double carFuel = Double.parseDouble(carTokens[1]);
        double carConsumption = Double.parseDouble(carTokens[2]);
        double truckFuel = Double.parseDouble(truckTokens[1]);
        double truckConsumption = Double.parseDouble(truckTokens[2]);
        Car car = new Car(carFuel,carConsumption);
        Truck truck = new Truck(truckFuel, truckConsumption);
        for (int i = 0; i < numberOfCommands; i++){
            String[] commands = reader.readLine().split(" ");
            String command = commands[0];
            String vehicleType = commands[1];
            double distanceOrFuel = Double.parseDouble(commands[2]);
            DecimalFormat df = new DecimalFormat("0.##");
            if(vehicleType.equals("Car")){
                if(command.equals("Drive")){
                    if(car.hasEnoughFuel(distanceOrFuel)){
                        car.drive(distanceOrFuel);
                        System.out.printf("Car travelled %s km\n", df.format(distanceOrFuel));
                    }else {
                        System.out.println("Car needs refueling");
                    }
                }else if(command.equals("Refuel")){
                    car.refuel(distanceOrFuel);
                }
            }else if(vehicleType.equals("Truck")){
                if(command.equals("Drive")){
                    if(truck.hasEnoughFuel(distanceOrFuel)){
                        truck.drive(distanceOrFuel);
                        System.out.printf("Truck travelled %s km\n", df.format(distanceOrFuel));
                    }else {
                        System.out.println("Truck needs refueling");
                    }
                }else if(command.equals("Refuel")){
                    truck.refuel(distanceOrFuel);
                }
            }
        }
        System.out.printf("Car: %.2f\n", car.getFuelQuantity());
        System.out.printf("Truck: %.2f\n", truck.getFuelQuantity());
    }
}
