package exerciseOOP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.instrument.IllegalClassFormatException;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine().toLowerCase();
        String[] foods = input.split(" ");
        Gandalf gandalf = new Gandalf(foods);
        System.out.println(gandalf.getHappiness());
        System.out.println(gandalf.getMood());
    }
}
