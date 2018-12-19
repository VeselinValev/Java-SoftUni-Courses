package inventoryTests;

import hell.entities.miscellaneous.HeroInventory;
import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.mockito.Mockito.mock;

public class InventoryTests {
    private Inventory heroInventory;
    private Item item;
    private Item item2;
    private Item item3;
    private Recipe recipe;

    @Before
    @SuppressWarnings("unchecked")
    public void before() throws NoSuchFieldException, IllegalAccessException {
        this.heroInventory = new HeroInventory();
        this.item = mock(Item.class);
        this.item2 = mock(Item.class);
        this.item3 = mock(Item.class);
        this.recipe = mock(Recipe.class);

        Mockito.when(item.getName()).thenReturn("nishto");
        Mockito.when(item2.getName()).thenReturn("nishto2");
        Mockito.when(item3.getName()).thenReturn("nishto3");

        Mockito.when(item.getHitPointsBonus()).thenReturn(1_000_000_000);
        Mockito.when(item2.getHitPointsBonus()).thenReturn(2_000_000_000);
        Mockito.when(item3.getHitPointsBonus()).thenReturn(1_000_000_000);

        Mockito.when(item.getIntelligenceBonus()).thenReturn(1_000_000_000);
        Mockito.when(item2.getIntelligenceBonus()).thenReturn(2_000_000_000);
        Mockito.when(item3.getIntelligenceBonus()).thenReturn(1_000_000_000);

        Mockito.when(item.getDamageBonus()).thenReturn(1_000_000_000);
        Mockito.when(item2.getDamageBonus()).thenReturn(2_000_000_000);
        Mockito.when(item3.getDamageBonus()).thenReturn(1_000_000_000);

        Mockito.when(item.getAgilityBonus()).thenReturn(1_000_000_000);
        Mockito.when(item2.getAgilityBonus()).thenReturn(2_000_000_000);
        Mockito.when(item3.getAgilityBonus()).thenReturn(1_000_000_000);

        Mockito.when(item.getStrengthBonus()).thenReturn(1_000_000_000);
        Mockito.when(item2.getStrengthBonus()).thenReturn(2_000_000_000);
        Mockito.when(item3.getStrengthBonus()).thenReturn(1_000_000_000);

        Mockito.when(this.recipe.getName()).thenReturn("salata");
        Mockito.when(this.recipe.getDamageBonus()).thenReturn(2_000_000);
        Mockito.when(this.recipe.getAgilityBonus()).thenReturn(2_000_000);
        Mockito.when(this.recipe.getHitPointsBonus()).thenReturn(2_000_000);
        Mockito.when(this.recipe.getIntelligenceBonus()).thenReturn(2_000_000);
        Mockito.when(this.recipe.getStrengthBonus()).thenReturn(2_000_000);
        Mockito.when(this.recipe.getRequiredItems()).thenReturn(new ArrayList<>(Arrays.asList("nishto", "nishto2", "nishto3")));

        Class newClass = this.heroInventory.getClass();
        Field commonItemsField = newClass.getDeclaredField("commonItems");
        commonItemsField.setAccessible(true);
        ((Map<String, Item>) commonItemsField.get(this.heroInventory)).put(this.item.getName(), item);
        ((Map<String, Item>) commonItemsField.get(this.heroInventory)).put(this.item2.getName(), item2);
        ((Map<String, Item>) commonItemsField.get(this.heroInventory)).put(this.item3.getName(), item3);

        Field recipeItemsField = newClass.getDeclaredField("recipeItems");
        recipeItemsField.setAccessible(true);
        ((Map<String, Recipe>) recipeItemsField.get(this.heroInventory)).put(this.recipe.getName(), this.recipe);
    }

    @Test
    public void getTotalStrengthBonusTest() {
        Assert.assertEquals("Perfect", this.heroInventory.getTotalStrengthBonus(), 4_000_000_000L);
    }

    @Test
    public void getTotalDamageBonusTest() {
        Assert.assertEquals("Perfect", this.heroInventory.getTotalDamageBonus(), 4_000_000_000L);
    }

    @Test
    public void getTotalIntelligenceTest() {
        Assert.assertEquals("Perfect", this.heroInventory.getTotalIntelligenceBonus(), 4_000_000_000L);
    }

    @Test
    public void getTotalAgilityBonusTest() {
        Assert.assertEquals("Perfect", this.heroInventory.getTotalAgilityBonus(), 4_000_000_000L);
    }

    @Test
    public void getTotalHitPointsBonus() {
        Assert.assertEquals("Perfect", this.heroInventory.getTotalHitPointsBonus(), 4_000_000_000L);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void addCommonItemShouldCreateNewItemAndRemoveRecipeComponents() throws NoSuchFieldException, IllegalAccessException {
        Item mock = mock(Item.class);
        long expectedCommonItemsLength = 2;

        this.heroInventory.addCommonItem(mock);
        Field commonItemsField = this.heroInventory.getClass().getDeclaredField("commonItems");
        commonItemsField.setAccessible(true);
        Map<String, Item> commonItems = (Map<String, Item>) commonItemsField.get(this.heroInventory);

        Assert.assertEquals("Common Items length is not correct", expectedCommonItemsLength, commonItems.size());

    }

    @Test
    @SuppressWarnings("unchecked")
    public void addRecipeItemShouldCreateNewItemAndRemoveRecipeComponents() throws NoSuchFieldException, IllegalAccessException {
        Recipe mock = mock(Recipe.class);
        long expectedRecipeItemsLength = 2;

        this.heroInventory.addRecipeItem(mock);
        Field recipeItemsField = this.heroInventory.getClass().getDeclaredField("recipeItems");
        recipeItemsField.setAccessible(true);
        Map<String, Item> recipeItems = (Map<String, Item>) recipeItemsField.get(this.heroInventory);

        Assert.assertEquals("Common Items length is not correct", expectedRecipeItemsLength, recipeItems.size());

    }
}