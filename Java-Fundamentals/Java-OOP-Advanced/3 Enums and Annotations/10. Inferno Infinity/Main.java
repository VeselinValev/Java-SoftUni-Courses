import enums.AmethystEnum;
import enums.EmeraldEnum;
import enums.RubyEnum;
import gems.Amethyst;
import gems.Emerald;
import gems.Gem;
import gems.Ruby;
import weapons.Axe;
import weapons.Knife;
import weapons.Sword;
import weapons.Weapon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Weapon> allWeapons = new LinkedHashMap<>();
        for (String input = reader.readLine(); !input.equals("END"); input = reader.readLine()) {
            String[] command = input.split(";");
            switch (command[0]) {
                case "Create":
                    switch (command[1]) {
                        case "AXE":
                            allWeapons.put(command[2], new Axe(command[2]));
                            break;
                        case "SWORD":
                            allWeapons.put(command[2], new Sword(command[2]));
                            break;
                        case "KNIFE":
                            allWeapons.put(command[2], new Knife(command[2]));
                            break;
                        default:
                            break;
                    }break;
                case "Remove":
                    try{
                        allWeapons.get(command[1]).removeGem(Integer.parseInt(command[2]));
                    }catch (IndexOutOfBoundsException iobe){
                        continue;
                    }
                    break;
                case "Add":
                    try{
                        Gem gem = null;
                        switch (command[3]){
                            case "RUBY":
                                gem = new Ruby(RubyEnum.STRENGTH.getValue(), RubyEnum.AGILITY.getValue(), RubyEnum.VITALITY.getValue());
                                break;
                            case "EMERALD":
                                gem = new Emerald(EmeraldEnum.STRENGTH.getValue(), EmeraldEnum.AGILITY.getValue(), EmeraldEnum.VITALITY.getValue());
                                break;
                            case "AMETHYST":
                                gem = new Amethyst(AmethystEnum.STRENGTH.getValue(), AmethystEnum.AGILITY.getValue(), AmethystEnum.VITALITY.getValue());
                                break;
                        }
                        allWeapons.get(command[1]).addGem(Integer.parseInt(command[2]), gem);
                    }catch (IndexOutOfBoundsException iobe){
                        continue;
                    }
                    break;
                case "Print":
                    System.out.println(allWeapons.get(command[1]).toString());
                    break;

            }
        }
    }
}
