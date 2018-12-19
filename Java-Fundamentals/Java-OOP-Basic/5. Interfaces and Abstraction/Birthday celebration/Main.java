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
        List<Living> livings = new ArrayList<>();
        while (true){
            String[] input = reader.readLine().split(" ");
            if ("end".equalsIgnoreCase(input[0])){
                break;
            }
            if (input[0].equals("Citizen")){
                livings.add(new Citizen(input[1], Integer.parseInt(input[2]), input[3], input[4]));
            }else if(input[0].equals("Pet")){
                livings.add(new Pet(input[1], input[2]));
            }
        }
        String year = reader.readLine();
        livings.stream().filter(x -> x.getBirthDate().endsWith(year)).forEach(x -> System.out.println(x.getBirthDate()));
    }
}


