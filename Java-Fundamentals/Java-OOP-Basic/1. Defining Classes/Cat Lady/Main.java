package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Cat> allCats = new ArrayList<>();
        for (String input = reader.readLine(); !input.equals("End"); input = reader.readLine()) {
            String[] tokens = input.split(" ");
            switch (tokens[0]){
                case "StreetExtraordinaire": allCats.add(new StreetExtraordinaire(tokens[0], tokens[1], Double.parseDouble(tokens[2])));break;
                case "Siamese": allCats.add(new Siamese(tokens[0], tokens[1], Double.parseDouble(tokens[2])));break;
                case "Cymric": allCats.add(new Cymric(tokens[0], tokens[1], Double.parseDouble(tokens[2])));break;
                default:break;
            }
        }
        String catName = reader.readLine();
        System.out.println(allCats.stream().filter(x -> x.getName().equals(catName)).findFirst().orElse(null).toString());
    }
}
