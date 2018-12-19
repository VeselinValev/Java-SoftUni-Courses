package hell.factories;

import hell.entities.heroes.Assassin;
import hell.entities.heroes.Barbarian;
import hell.entities.heroes.Wizard;
import hell.interfaces.Hero;
import hell.interfaces.Inventory;

public class HeroFactory {

    public static Hero createBarbarian(String name, int[] stats){
        Inventory inventory = InventoryFactory.createInventory();
        return new Barbarian(name, stats[0], stats[1], stats[2], stats[3], stats[4], inventory);
    }
    public static Hero createAssassin(String name, int[] stats){
        Inventory inventory = InventoryFactory.createInventory();
        return new Assassin(name, stats[0], stats[1], stats[2], stats[3], stats[4], inventory);
    }
    public static Hero createWizard(String name, int[] stats){
        Inventory inventory = InventoryFactory.createInventory();
        return new Wizard(name, stats[0], stats[1], stats[2], stats[3], stats[4], inventory);
    }
}
