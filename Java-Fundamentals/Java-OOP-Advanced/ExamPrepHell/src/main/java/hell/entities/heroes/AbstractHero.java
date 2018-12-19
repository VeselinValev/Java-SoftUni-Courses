package hell.entities.heroes;

import hell.annotations.ItemCollection;
import hell.interfaces.Hero;
import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public abstract class AbstractHero implements Hero {

    private String name;
    private int strength;
    private int agility;
    private int intelligence;
    private int hitPoints;
    private int damage;
    private Inventory inventory;

    AbstractHero(String name, int strength, int agility, int intelligence, int hitPoints, int damage, Inventory inventory) {

        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.inventory = inventory;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getStrength() {
        return this.strength + this.inventory.getTotalStrengthBonus();
    }

    @Override
    public long getAgility() {
        return this.agility + this.inventory.getTotalAgilityBonus();
    }

    @Override
    public long getIntelligence() {
        return this.intelligence + this.inventory.getTotalIntelligenceBonus();
    }

    @Override
    public long getHitPoints() {
        return this.hitPoints + this.inventory.getTotalHitPointsBonus();
    }

    @Override
    public long getDamage() {
        return this.damage + + this.inventory.getTotalDamageBonus();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Item> getItems() throws IllegalAccessException {
        Collection<Item> result = new ArrayList<>();
        Field[] fields = this.inventory.getClass().getDeclaredFields();
        for (Field field: fields){
            if (field.isAnnotationPresent(ItemCollection.class)){
                field.setAccessible(true);
                result = ((Map<String, Item>)field.get(this.inventory)).values();
                break;
            }
        }
        return result;
    }

    @Override
    public void addItem(Item item) {
        this.inventory.addCommonItem(item);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        this.inventory.addRecipeItem(recipe);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Hero: %s, Class: %s%n", this.getName(), this.getClass().getSimpleName()))
                .append(String.format("HitPoints: %s, Damage: %s%n", this.getHitPoints(), this.getDamage()))
                .append(String.format("Strength: %s%n", this.getStrength()))
                .append(String.format("Agility: %s%n", this.getAgility()))
                .append(String.format("Intelligence: %s%n", this.getIntelligence())).append("Items:");
        try {
            if (this.getItems().isEmpty()){
                sb.append(" None");
            }else{
                for (Item item: this.getItems()){
                    sb.append("\n").append(item.toString());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
