package hell.factories;

import hell.entities.items.CommonItem;
import hell.entities.items.RecipeItem;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ItemFactory {

    public static Item createCommonItem(String[] params){
        return new CommonItem(params[1], Integer.parseInt(params[3]), Integer.parseInt(params[4])
                ,Integer.parseInt(params[5]),Integer.parseInt(params[6]),Integer.parseInt(params[7]));
    }
    public static Item createCommonItem(Recipe recipe){
        return new CommonItem(recipe.getName(), recipe.getStrengthBonus(), recipe.getAgilityBonus(),
                recipe.getIntelligenceBonus(), recipe.getHitPointsBonus(), recipe.getDamageBonus());
    }
    public static Recipe createRecipeItem(String[] params){
        List<String> requiredItems = Arrays.stream(params).skip(8).collect(Collectors.toList());
        return new RecipeItem(params[1], Integer.parseInt(params[3]), Integer.parseInt(params[4])
                ,Integer.parseInt(params[5]),Integer.parseInt(params[6]),Integer.parseInt(params[7]), requiredItems);
    }
}
