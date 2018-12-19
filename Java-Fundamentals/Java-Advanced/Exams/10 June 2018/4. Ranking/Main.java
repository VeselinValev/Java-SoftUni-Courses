import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> allContests = new LinkedHashMap<>();
        String input;
        while (true){
            input = reader.readLine();
            if (input.equalsIgnoreCase("end of contests")){
                break;
            }
            String[] tokens = input.split(":");
            allContests.putIfAbsent(tokens[0], tokens[1]);
        }
        Map<String, Map<String, Integer>> allUsers = new LinkedHashMap<>();
        String input2;
        while (true){
            input2 = reader.readLine();
            if(input2.equalsIgnoreCase("end of submissions")){
                break;
            }
            String[] tokens = input2.split("=>");
            String contest = tokens[0];
            String password = tokens[1];
            String userName = tokens[2];
            int points = Integer.parseInt(tokens[3]);
            if (allContests.containsKey(contest)){
                if (allContests.get(contest).equals(password)){
                    allUsers.putIfAbsent(userName, new LinkedHashMap<>());
                    if (allUsers.get(userName).containsKey(contest)){
                        if (points > allUsers.get(userName).get(contest)){
                            allUsers.get(userName).put(contest, points);
                        }
                    }else{
                        allUsers.get(userName).put(contest, points);
                    }
                }
            }
        }
        allUsers = allUsers.entrySet().stream().sorted((x,y) -> {
            long sum1 = x.getValue().values().stream().collect(Collectors.summarizingInt(Integer::valueOf)).getSum();
            long sum2 = y.getValue().values().stream().collect(Collectors.summarizingInt(Integer::valueOf)).getSum();
            return Long.compare(sum2, sum1);
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b)-> a, LinkedHashMap::new));
        int counter = 0;
        for (Map.Entry<String, Map<String, Integer>> user: allUsers.entrySet()){
            if (counter++ == 1){
                break;
            }
            long sum = user.getValue().values().stream().collect(Collectors.summarizingInt(Integer::valueOf)).getSum();
            System.out.printf("Best candidate is %s with total %s points.%n", user.getKey(), sum);
            System.out.println("Ranking:");
        }
        allUsers.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).forEach(a -> {
            System.out.printf("%s%n", a.getKey());
            a.getValue().entrySet().stream().sorted((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue())).forEach(b -> {
                System.out.printf("#  %s -> %s%n", b.getKey(), b.getValue());
            });
        });
    }
}

