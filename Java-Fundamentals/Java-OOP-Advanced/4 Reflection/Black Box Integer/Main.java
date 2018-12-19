package pr02PrivateClassFiddling;

import pr02PrivateClassFiddling.com.peshoslav.BlackBoxInt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Class<?> cl = BlackBoxInt.class;
        Constructor constructor = cl.getDeclaredConstructor();
        constructor.setAccessible(true);
        BlackBoxInt object = (BlackBoxInt) constructor.newInstance();
        for (String input = reader.readLine(); !input.equals("END"); input = reader.readLine()) {
            String[] tokens = input.split("_");
            String operation = tokens[0];
            int value = Integer.parseInt(tokens[1]);
            Method method = object.getClass().getDeclaredMethod(operation, int.class);
            method.setAccessible(true);
            method.invoke(object, value);
            Field field = object.getClass().getDeclaredField("innerValue");
            field.setAccessible(true);
            System.out.println(field.get(object));
        }
    }
}

