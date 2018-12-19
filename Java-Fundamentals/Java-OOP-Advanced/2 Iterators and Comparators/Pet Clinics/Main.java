import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static Map<String, Clinic> allClinics = new HashMap<>();
    public static Map<String, Pet> allPets = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split(" ");
            try {
                switch (input[0]) {
                    case "Create":
                        if (input[1].equals("Pet")) {
                            allPets.put(input[2], new Pet(input[2], Integer.parseInt(input[3]), input[4]));
                        } else {
                            allClinics.put(input[2], new Clinic(input[2], Integer.parseInt(input[3])));
                        }
                        break;
                    case "Add":
                        if (!allClinics.containsKey(input[2]) || !allPets.containsKey(input[1])) {
                            throw new IllegalArgumentException("Invalid Operation!");
                        }
                        System.out.println(allClinics.get(input[2]).add(allPets.get(input[1])));
                        break;
                    case "Release":
                        if (!allClinics.containsKey(input[1])) {
                            throw new IllegalArgumentException("Invalid Operation!");
                        }
                        System.out.println(allClinics.get(input[1]).release());
                        break;
                    case "HasEmptyRooms":
                        if (!allClinics.containsKey(input[1])) {
                            throw new IllegalArgumentException("Invalid Operation!");
                        }
                        System.out.println(allClinics.get(input[1]).hasEmptyRooms());
                        break;
                    case "Print":
                        if (!allClinics.containsKey(input[1])) {
                            throw new IllegalArgumentException("Invalid Operation!");
                        }
                        if (input.length == 2) {

                            System.out.println(allClinics.get(input[1]).printAllRooms());
                        } else {
                            System.out.println(allClinics.get(input[1]).printRoom(Integer.parseInt(input[2])));
                        }
                        break;
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        }
    }
}