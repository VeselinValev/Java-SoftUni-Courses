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
        String[] busTokens = reader.readLine().split(" ");
        int numberOfCommands = Integer.parseInt(reader.readLine());

        double carFuel = Double.parseDouble(carTokens[1]);
        double carConsumption = Double.parseDouble(carTokens[2]);
        double carTankCapacity = Double.parseDouble(carTokens[3]);
        double truckFuel = Double.parseDouble(truckTokens[1]);
        double truckConsumption = Double.parseDouble(truckTokens[2]);
        double truckTankCapacity = Double.parseDouble(truckTokens[3]);
        double busFuel = Double.parseDouble(busTokens[1]);
        double busConsumption = Double.parseDouble(busTokens[2]);
        double busTankCapacity = Double.parseDouble(busTokens[3]);

        Car car = new Car(carFuel,carConsumption, carTankCapacity);
        Truck truck = new Truck(truckFuel, truckConsumption, truckTankCapacity);
        Bus bus = new Bus(busFuel, busConsumption, busTankCapacity);

        for (int i = 0; i < numberOfCommands; i++){
            try{
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
                else if(vehicleType.equals("Bus")) {
                    if (command.equals("DriveEmpty")) {
                        if (bus.hasEnoughFuel(distanceOrFuel)) {
                            bus.drive(distanceOrFuel);
                            System.out.printf("Bus travelled %s km\n", df.format(distanceOrFuel));
                        } else {
                            System.out.println("Bus needs refueling");
                        }
                    } else if (command.equals("Refuel")) {
                        bus.refuel(distanceOrFuel);
                    } else if (command.equals("Drive")) {
                        if (bus.hasEnoughFuelIfFull(distanceOrFuel)) {
                            bus.driveFull(distanceOrFuel);
                            System.out.printf("Bus travelled %s km\n", df.format(distanceOrFuel));
                        } else {
                            System.out.println("Bus needs refueling");
                        }
                    }
                }
            }catch (IllegalArgumentException iae){
                System.out.println(iae.getMessage());
            }
        }
        System.out.printf("Car: %.2f\n", car.getFuelQuantity());
        System.out.printf("Truck: %.2f\n", truck.getFuelQuantity());
        System.out.printf("Bus: %.2f\n", bus.getFuelQuantity());
    }
}
