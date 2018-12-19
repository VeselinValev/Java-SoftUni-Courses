package MilitaryElite;

import MilitaryElite.Interfaces.*;
import MilitaryElite.Soldiers.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Private> allPrivates = new ArrayList<>();
        List<Soldier> allSoldiers = new ArrayList<>();
        while (true) {
            String[] input = reader.readLine().split(" ");
            if ("end".equalsIgnoreCase(input[0])) {
                break;
            } else if ("Private".equals(input[0])) {
                Private currentPrivate = new PrivateImpl(input[2], input[3], Integer.parseInt(input[1]), Double.parseDouble(input[4]));
                allPrivates.add(currentPrivate);
                allSoldiers.add(currentPrivate);
            } else if ("LeutenantGeneral".equals(input[0])) {
                LeutenantGeneral leutenantGeneral = new LeutenantGeneralImpl(input[2], input[3], Integer.parseInt(input[1]), Double.parseDouble(input[4]));
                for (int i = 5; i < input.length; i++) {
                    int temp = i;
                    if (leutenantGeneral.getSetOfPrivates().stream().noneMatch(x -> x.getId() == Integer.parseInt(input[temp]))) {
                        leutenantGeneral.getSetOfPrivates().add(allPrivates.stream().filter(x -> x.getId() == Integer.parseInt(input[temp])).findFirst().orElse(null));
                    }
                }
                allSoldiers.add(leutenantGeneral);
            } else if ("Engineer".equals(input[0])) {
                if (input[5].equals("Airforces") || input[5].equals("Marines")) {
                    List<Repair> list = new ArrayList<>();
                    for (int i = 6; i < input.length; i+=2) {
                        list.add(new RepairImpl(input[i], Integer.parseInt(input[i + 1])));
                    }
                    Engineer engineer = new EngineerImpl(input[2], input[3], Integer.parseInt(input[1]), Double.parseDouble(input[4]), input[5], list);
                    allSoldiers.add(engineer);
                }
            } else if ("Commando".equals(input[0])) {
                if (input[5].equals("Airforces") || input[5].equals("Marines")) {
                    List<Mission> list = new ArrayList<>();
                    for (int i = 6; i < input.length; i+=2) {
                        if(input[i + 1].equals("Finished") || input[i + 1].equals("inProgress")){
                            list.add(new MissionImpl(input[i], input[i + 1]));
                        }
                    }
                    Commando commando = new CommandoImpl(input[2], input[3], Integer.parseInt(input[1]), Double.parseDouble(input[4]),  input[5], list);
                    allSoldiers.add(commando);
                }
            }else if ("Spy".equals(input[0])) {
                Spy spy = new SpyImpl(input[2], input[3], Integer.parseInt(input[1]), input[4]);
                allSoldiers.add(spy);
            }
        }
        for (Soldier soldier: allSoldiers){
            System.out.println(soldier.toString().trim());
        }
    }
}

