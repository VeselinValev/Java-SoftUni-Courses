import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfLines = 0;
        List<String> allCrates = new ArrayList<>();
        for (String input = reader.readLine(); !input.equals("Collect"); input = reader.readLine()){
            Pattern findCrates = Pattern.compile("\\[.*?]");
            Matcher crateMatcher = findCrates.matcher(input);
            while (crateMatcher.find()) {
                allCrates.add(crateMatcher.group());
            }
            numberOfLines++;
        }
        int foodAmount = 0;
        int drinkAmount = 0;
        int counter = 0;
        int N = allCrates.size() / numberOfLines;
        Pattern foodCratePattern = Pattern.compile("\\[(#([0-9]{" + N + "}))([A-Za-z0-9\\s]+)?\\1]");
        Pattern drinkCratePattern = Pattern.compile("\\[(#([a-z]{" + N + "}))([A-Za-z0-9\\s]+)?\\1]");
        for (String crate : allCrates) {
            Matcher foodCrateMatcher = foodCratePattern.matcher(crate);
            Matcher drinkCrateMatcher = drinkCratePattern.matcher(crate);
            if (foodCrateMatcher.find()) {
                foodAmount += findFoodAmount(foodCrateMatcher.group(3), foodCrateMatcher.group(2));
                counter++;
            }
            if (drinkCrateMatcher.find()) {
                drinkAmount += findDrinkAmount(drinkCrateMatcher.group(3), drinkCrateMatcher.group(2));
                counter++;
            }
        }
        if (counter != 0) {
            System.out.println("Number of supply crates: " + counter);
            System.out.println("Amount of food collected: " + foodAmount);
            System.out.println("Amount of drinks collected: " + drinkAmount);
        } else {
            System.out.println("No supplies found!");
        }
    }
    private static int findFoodAmount(String body, String tag) {
        int bodySum = 0;
        HashSet<String> uniqueSymbols = Arrays.stream(body.split("")).distinct().collect(Collectors.toCollection(HashSet::new));
        for (String symbol : uniqueSymbols) {
            bodySum += symbol.charAt(0);
        }
        return bodySum * tag.length();
    }
    private static int findDrinkAmount(String body, String tag) {
        int bodySum = 0;
        int tagSum = 0;
        for (char symbol : body.toCharArray()) {
            bodySum += symbol;
        }
        for (char symbol : tag.toCharArray()) {
            tagSum += symbol;
        }
        return bodySum * tagSum;
    }
}


