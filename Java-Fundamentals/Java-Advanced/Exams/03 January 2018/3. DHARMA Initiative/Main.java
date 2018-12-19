import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Map<Long, String>> allStations = Map.of("Arrow", new LinkedHashMap<>(), "Hydra", new LinkedHashMap<>(),
                "Orchid", new LinkedHashMap<>(),"Pearl", new LinkedHashMap<>(), "Flame", new LinkedHashMap<>());
        Map<String, String> stationPurpose = Map.of("Arrow", "Development of defensive strategies, and Intelligence gathering.",
                "Hydra", "Zoological Research.",
                "Orchid", "Space-time manipulation research, disguised as a Botanical station.",
                "Pearl", "Psychological Research and/or Observation.",
                "Flame", "Communication.");
        String input;
        while (!(input = reader.readLine()).equals("Recruit")) {
            String[] tokens = input.split(":");
            if (allStations.containsKey(tokens[2])) {
                allStations.get(tokens[2]).put(Long.parseLong(tokens[1]), tokens[0]);
            }
        }
        String command = reader.readLine();
        if (command.equals("DHARMA Initiative")) {
            allStations.entrySet().stream().sorted((o1, o2) -> {
                int compare = Integer.compare(o2.getValue().size(), o1.getValue().size());
                if (compare == 0) {
                    compare = o1.getKey().compareTo(o2.getKey());
                }
                return compare;
            }).forEach(x -> System.out.printf("The %s has %s DHARMA recruits in it.\n", x.getKey(), x.getValue().size()));
        } else {
            if (allStations.containsKey(command)) {
                System.out.printf("The %s station: %s\n", command, stationPurpose.get(command));
                if (!allStations.get(command).isEmpty()) {
                    allStations.get(command).entrySet().stream().sorted((o1, o2) -> {
                        int compare = Long.compare(o2.getKey(), o1.getKey());
                        if (compare == 0) {
                            compare = o1.getKey().compareTo(o2.getKey());
                        }
                        return compare;
                    }).forEach(x -> System.out.printf("###%s - %s\n", x.getValue(), x.getKey()));
                } else {
                    System.out.println("No recruits.");
                }
            } else {
                System.out.println("DHARMA Initiative does not have such a station!");
            }
        }
    }
}

