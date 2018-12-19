package hell.entities.heroes;

import hell.interfaces.Inventory;

public class Assassin extends AbstractHero {
    public Assassin(String name, int strength, int agility, int intelligence, int hitPoints, int damage, Inventory inventory) {
        super(name, strength, agility, intelligence, hitPoints, damage, inventory);
    }
}
