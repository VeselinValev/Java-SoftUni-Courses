package Avatar;

import Avatar.Benders.*;
import Avatar.Monuments.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<Nation> allNations = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> issuedWars = new ArrayList<>();
        for (String input = reader.readLine(); !input.equals("Quit"); input = reader.readLine()) {
            String[] tokens = input.split(" ");
            String element = tokens[1];
            Nation currentNation = findNation(element);;
            switch (tokens[0]) {
                case "Bender":
                    currentNation.addBender(createBender(element, tokens[2], Integer.parseInt(tokens[3]), Double.parseDouble(tokens[4])));
                    break;
                case "Monument":
                    currentNation.addMonument(createMonument(element, tokens[2], Integer.parseInt(tokens[3])));
                    break;
                case "War":
                    issuedWars.add(element);
                    for (Nation nation: allNations){
                        nation.calculateTotalPower();
                    }
                    Nation winner = findWinner();
                    destroyBendersAndMonuments(winner.getName());
                    break;
                case "Status":
                    System.out.print(currentNation.toString());
            }
        }
        for (int i = 0; i < issuedWars.size(); i++){
            System.out.printf("War %s issued by %s%n", i + 1, issuedWars.get(i));
        }
    }

    public static Bender createBender(String element, String name, int power, double special){
        Bender bender = null;
        switch (element) {
            case "Air": bender = new AirBender(name, power, special);break;
            case "Water": bender = new WaterBender(name, power, special);break;
            case "Fire": bender = new FireBender(name, power, special);break;
            case "Earth": bender = new EarthBender(name, power, special);break;
        }
        return bender;
    }

    public static Nation findNation(String name){
        if (allNations.stream().noneMatch(x -> x.getName().equals(name))){
            allNations.add(new Nation(name));
        }
        return allNations.stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null);
    }

    public static Monument createMonument(String element, String name, int special){
        Monument monument = null;
        switch (element) {
            case "Air": monument = new AirMonument(name, special);break;
            case "Water": monument = new WaterMonument(name, special);break;
            case "Fire": monument = new FireMonument(name, special);break;
            case "Earth": monument = new EarthMonument(name, special);break;
        }
        return monument;
    }

    public static Nation findWinner(){
        double biggestPower = 0;
        for (Nation nation: allNations){
            if (biggestPower < nation.getTotalPower()){
                biggestPower = nation.getTotalPower();
            }
        }
        double power = biggestPower;
        return allNations.stream().filter(x -> x.getTotalPower() == power).findFirst().orElse(null);
    }

    public  static void destroyBendersAndMonuments(String element){
        for (Nation nation: allNations){
            if (!nation.getName().equals(element)){
                nation.getBenders().clear();
                nation.getMonuments().clear();
            }
        }
    }
}
