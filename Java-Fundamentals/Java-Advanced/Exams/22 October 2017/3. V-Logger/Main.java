import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, TreeSet<String>> vloggers = new LinkedHashMap<>();
        Map<String, TreeSet<String>> followedVloggers = new LinkedHashMap<>();
        while (true) {
            String[] tokens = reader.readLine().split("\\s+");
            if ("Statistics".equals(tokens[0])) {
                break;
            }
            if ("joined".equals(tokens[1])) {
                String vlogger = tokens[0];
                vloggers.putIfAbsent(vlogger, new TreeSet<>());
                followedVloggers.putIfAbsent(vlogger, new TreeSet<>());
            } else {
                String followerName = tokens[0];
                String vlogger = tokens[2];
                if (!vloggers.containsKey(followerName) || !vloggers.containsKey(vlogger) || followerName.equals(vlogger)) {
                    continue;
                }
                vloggers.get(vlogger).add(followerName);
                followedVloggers.get(followerName).add(vlogger);
            }
        }
        System.out.printf("The V-Logger has a total of %s vloggers in its logs.%n", vloggers.size());
        vloggers = vloggers.entrySet().stream().sorted((o1, o2) -> {
            int compare = Integer.compare(o2.getValue().size(), o1.getValue().size());
            if (compare == 0) {
                compare = Integer.compare(followedVloggers.get(o1.getKey()).size(), followedVloggers.get(o2.getKey()).size());
            }
            return compare;
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
        Map.Entry<String, TreeSet<String>> firstVlogger = vloggers.entrySet().stream().findFirst().orElse(null);
        int counter = 1;
        System.out.printf("%s. %s : %s followers, %s following%n", counter, firstVlogger.getKey(), firstVlogger.getValue().size(), followedVloggers.get(firstVlogger.getKey()).size());
        for (String follower : firstVlogger.getValue()) {
            System.out.printf("*  %s%n", follower);
        }
        vloggers.remove(firstVlogger.getKey());
        for (Map.Entry<String, TreeSet<String>> follower : vloggers.entrySet()) {
            System.out.printf("%s. %s : %s followers, %s following%n", ++counter, follower.getKey(), follower.getValue().size(), followedVloggers.get(follower.getKey()).size());
        }
    }
}
