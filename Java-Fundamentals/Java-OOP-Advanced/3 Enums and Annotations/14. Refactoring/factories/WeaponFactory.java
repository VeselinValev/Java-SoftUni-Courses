package factories;

import contracts.Weapon;
import entities.weapons.Axe;
import entities.weapons.Knife;
import entities.weapons.Sword;

public class WeaponFactory {
    public static Weapon createWeapon(String weaponName, String weaponType){
        switch (weaponType) {
            case "AXE":
                return new Axe(weaponName);
            case "SWORD":
                return new Sword(weaponName);
            case "KNIFE":
                return new Knife(weaponName);
        }
        return null;
    }
}
