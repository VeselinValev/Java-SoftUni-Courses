import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> allGenes = new LinkedHashMap<>();
        for (String input = reader.readLine(); !input.equals("Stop!"); input = reader.readLine()){
            Pattern pattern = Pattern.compile("([!@#$?a-z]+)=([0-9]+)--([0-9]+)<<([a-z]+)");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()){
                String geneName = getGeneName(matcher.group(1));
                int nameLength = Integer.parseInt(matcher.group(2));
                int countOfGenes = Integer.parseInt(matcher.group(3));
                String organismName = matcher.group(4);
                if (geneName.length() != nameLength){
                    continue;
                }
                allGenes.putIfAbsent(organismName, 0);
                allGenes.put(organismName, allGenes.get(organismName) + countOfGenes);
            }
        }
        allGenes.entrySet().stream().sorted((x,y) -> Integer.compare(y.getValue(), x.getValue())).forEach(x -> {
            System.out.printf("%s has genome size of %s%n", x.getKey(), x.getValue());
        });
    }
    public static String getGeneName(String name){
        StringBuilder sb = new StringBuilder();
        for (char symbol: name.toCharArray()){
            if (Character.isAlphabetic(symbol)){
                sb.append(symbol);
            }
        }
        return sb.toString();
    }
}

