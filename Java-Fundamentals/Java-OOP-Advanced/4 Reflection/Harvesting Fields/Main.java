package pr01HarvestingFields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Class cl = RichSoilLand.class;
        Field[] fields = cl.getDeclaredFields();

        for (String input = reader.readLine(); !input.equals("HARVEST"); input = reader.readLine()) {

            switch (input) {
                case "private":
                    printFields(2, fields);
                    break;
                case "protected":
                    printFields(4, fields);
                    break;
                case "public":
                    printFields(1, fields);
                    break;
                case "all":
                    Arrays.stream(fields).forEach(x -> System.out.println(Modifier.toString(x.getModifiers()) +
                            " " + x.getType().getSimpleName() + " " + x.getName()));
                    break;
            }
        }
    }

    public static void printFields(int fieldType, Field[] fields) {
        Arrays.stream(fields).filter(x -> x.getModifiers() == fieldType)
                .forEach(x -> System.out.println(Modifier.toString(x.getModifiers()) +
                        " " + x.getType().getSimpleName() + " " + x.getName()));
    }
}
