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

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(reader.readLine());
        List<Buyable> buyableList = new ArrayList<>();
        for (int i = 0; i < num; i++){
            String[] input = reader.readLine().split(" ");
            if (input.length == 4){
                buyableList.add(new Citizen(input[0], Integer.parseInt(input[1]), input[2], input[3]));
            }else{
                buyableList.add(new Rebel(input[0], Integer.parseInt(input[1]), input[2]));
            }
        }
        while (true){
            String input = reader.readLine();
            if ("end".equalsIgnoreCase(input)){
                break;
            }
            if(buyableList.stream().anyMatch(x -> x.getName().equals(input))){
                Buyable temp = buyableList.stream().filter(x -> x.getName().equals(input)).findFirst().orElse(null);
                temp.buyFood();
            }
        }
        System.out.println(buyableList.stream().mapToInt(Buyable::getFoodAmount).sum());
    }
}


