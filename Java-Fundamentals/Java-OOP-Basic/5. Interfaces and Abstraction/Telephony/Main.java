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
        List<String> numbers = Arrays.stream(reader.readLine().split(" ")).collect(Collectors.toList());
        List<String> webSites = Arrays.stream(reader.readLine().split(" ")).collect(Collectors.toList());
        Smartphone smartphone = new Smartphone();
        for (String number: numbers){
            if(checkIfNumber(number)){
                System.out.println(smartphone.calling(number));
            }else{
                System.out.println("Invalid number!");
            }
        }
        for (String webSite: webSites){
            if(checkIfNotNumber(webSite)){
                System.out.println(smartphone.browsing(webSite));
            }else{
                System.out.println("Invalid URL!");
            }
        }

    }
    public static boolean checkIfNumber(String number){
        for (int i = 0; i < number.length(); i++){
            if(!Character.isDigit(number.charAt(i))){
                return false;
            }
        }
        return true;
    }
    public static boolean checkIfNotNumber(String webSite){
        for (int i = 0; i < webSite.length(); i++){
            if(Character.isDigit(webSite.charAt(i))){
                return false;
            }
        }
        return true;
    }
}


