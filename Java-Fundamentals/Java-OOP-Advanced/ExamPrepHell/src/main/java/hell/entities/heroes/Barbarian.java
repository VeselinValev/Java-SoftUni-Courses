package hell.entities.heroes;

import hell.interfaces.Inventory;

public class Barbarian extends AbstractHero {

    public Barbarian(String name, int strength, int agility, int intelligence, int hitPoints, int damage, Inventory inventory) {
        super(name, strength, agility, intelligence, hitPoints, damage, inventory);
    }
}
