package com.company;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> peopleInfo = new LinkedHashMap<>();
        List<Pair<String, String>> relativesInfo = new ArrayList<>();
        String mainPerson = reader.readLine();
        for (String input = reader.readLine(); !input.equals("End"); input = reader.readLine()) {
            if (input.contains(" - ")){
                String[] tokens = input.split(" - ");
                relativesInfo.add(new Pair<>(tokens[0], tokens[1]));
            }else{
                String[] tokens = input.split(" ");
                peopleInfo.put(tokens[0] + " " + tokens[1], tokens[2]);
            }
        }
        Person person;
        if (peopleInfo.containsKey(mainPerson)){
            person = new Person(mainPerson, peopleInfo.get(mainPerson));
        }else{
            Map.Entry<String, String> personInfo = peopleInfo.entrySet().stream()
                    .filter(x -> x.getValue().equals(mainPerson)).findFirst().orElse(null);
            person = new Person(personInfo.getKey(), personInfo.getValue());
        }
        findChildren(person, relativesInfo, peopleInfo);
        findParents(person, relativesInfo, peopleInfo);
        System.out.println(person.toString());
    }

    public static void findChildren(Person person, List<Pair<String, String>> relativesInfo, Map<String, String> peopleInfo){
        for (Pair<String, String> pair: relativesInfo){
            if (person.getName().equals(pair.getKey()) || person.getBirthDate().equals(pair.getKey())){
                String childName;
                String childBirthDate;
                if (pair.getValue().contains("/")){
                    childBirthDate = pair.getValue();
                    Map.Entry<String, String> personInfo = peopleInfo.entrySet().stream()
                            .filter(x -> x.getValue().equals(childBirthDate)).findFirst().orElse(null);
                    childName = personInfo.getKey();
                }else{
                    childName = pair.getValue();
                    childBirthDate = peopleInfo.get(childName);
                }
                person.addChildren(new Children(childName, childBirthDate));
            }
        }
    }

    public static void findParents(Person person, List<Pair<String, String>> relativesInfo, Map<String, String> peopleInfo){
        for (Pair<String, String> pair: relativesInfo){
            if (person.getName().equals(pair.getValue()) || person.getBirthDate().equals(pair.getValue())){
                String parentName;
                String parentBirthDate;
                if (pair.getKey().contains("/")){
                    parentBirthDate = pair.getKey();
                    Map.Entry<String, String> personInfo = peopleInfo.entrySet().stream()
                            .filter(x -> x.getValue().equals(parentBirthDate)).findFirst().orElse(null);
                    parentName = personInfo.getKey();
                }else{
                    parentName = pair.getKey();
                    parentBirthDate = peopleInfo.get(parentName);
                }
                person.addParents(new Parent(parentName, parentBirthDate));
            }
        }
    }
}
