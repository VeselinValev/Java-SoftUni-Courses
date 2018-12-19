package hell.interfaces;

public interface Controller {
    String createHero(String heroName, String heroType);

    String createCommonItem(String[] params);

    String createRecipeItem(String[] params);

    String inspect(String heroName);

    String quit();
}
