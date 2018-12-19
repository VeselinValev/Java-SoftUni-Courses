package hell.controllers;

import hell.comparators.HeroComparator;
import hell.constants.HeroBaseStats;
import hell.constants.Messages;
import hell.factories.HeroFactory;
import hell.factories.ItemFactory;
import hell.interfaces.Controller;
import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HeroController implements Controller {
    private Map<String, Hero> allHeroes;

    public HeroController() {
        this.allHeroes = new LinkedHashMap<>();
    }

    @Override
    public String createHero(String heroName, String heroType){
        Hero newHero;
        if (heroType.equals("Barbarian")) {
            newHero = HeroFactory.createBarbarian(heroName, HeroBaseStats.BARBARIAN);
        } else if (heroType.equals("Assassin")) {
            newHero = HeroFactory.createAssassin(heroName, HeroBaseStats.ASSASSIN);
        } else {
            newHero = HeroFactory.createWizard(heroName, HeroBaseStats.WIZARD);
        }
        this.allHeroes.putIfAbsent(heroName, newHero);
        return String.format(Messages.HERO_CREATED, heroType, heroName);
    }

    @Override
    public String createCommonItem(String[] params){
        Item newItem = ItemFactory.createCommonItem(params);
        this.allHeroes.get(params[2]).addItem(newItem);
        return String.format(Messages.ITEM_CREATED, params[1], params[2]);
    }

    @Override
    public String createRecipeItem(String[] params){
        Recipe newRecipe = ItemFactory.createRecipeItem(params);
        this.allHeroes.get(params[2]).addRecipe(newRecipe);
        return String.format(Messages.RECIPE_CREATED, params[1], params[2]);
    }

    @Override
    public String inspect(String heroName){
        return this.allHeroes.get(heroName).toString();
    }

    @Override
    public String quit(){
        List<Hero> sortedHeroes = new ArrayList<>(this.allHeroes.values());
        sortedHeroes.sort(new HeroComparator());
        int counter = 1;
        StringBuilder sb = new StringBuilder();
        for (Hero hero: sortedHeroes){
            sb.append(String.format("%d. %s: %s%n", counter++, hero.getClass().getSimpleName(), hero.getName()))
                    .append(String.format("###HitPoints: %s%n", hero.getHitPoints()))
                    .append(String.format("###Damage: %s%n", hero.getDamage()))
                    .append(String.format("###Strength: %s%n", hero.getStrength()))
                    .append(String.format("###Agility: %s%n", hero.getAgility()))
                    .append(String.format("###Intelligence: %s%n", hero.getIntelligence()))
                    .append("###Items: ");
            try {
                if (hero.getItems().isEmpty()){
                    sb.append("None\n");
                }else{
                    List<String> itemNames = hero.getItems().stream().map(Item::getName).collect(Collectors.toList());
                    sb.append(String.join(", ", itemNames)).append("\n");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return sb.substring(0, sb.length() - 1);
    }
}
