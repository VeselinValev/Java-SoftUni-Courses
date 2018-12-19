package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException, IllegalClassFormatException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Identifiable> identifiables = new ArrayList<>();
        while (true){
            String[] input = reader.readLine().split(" ");
            if ("end".equalsIgnoreCase(input[0])){
                break;
            }
            if (input.length == 3){
                identifiables.add(new Citizen(input[0], Integer.parseInt(input[1]), input[2]));
            }else{
                identifiables.add(new Robot(input[0], input[1]));
            }
        }
        String identifier = reader.readLine();
        for (Identifiable unit: identifiables){
            if (unit.getId().endsWith(identifier)){
                System.out.println(unit.getId());
            }
        }
    }
}


