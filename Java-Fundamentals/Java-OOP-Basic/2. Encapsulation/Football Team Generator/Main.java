package exerciseOOP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        Map<String, Team> teams = new HashMap<>();
        try {
            while (true) {
                input = reader.readLine();
                if (input.equals("END")) {
                    break;
                }
                String[] tokens = input.split(";");
                switch (tokens[0]) {
                    case "Team":
                        Team team = new Team(tokens[1]);
                        teams.put(tokens[1], team);
                        break;
                    case "Add":
                        if (teams.containsKey(tokens[1])) {
                            double endurance = Double.parseDouble(tokens[3]);
                            if(endurance < 0 || endurance > 100){
                                System.out.println("Endurance should be between 0 and 100.");
                                continue;
                            }
                            double sprint = Double.parseDouble(tokens[4]);
                            if(sprint < 0 || sprint > 100){
                                System.out.println("Sprint should be between 0 and 100.");
                                continue;
                            }
                            double dribble = Double.parseDouble(tokens[5]);
                            if(dribble < 0 || dribble > 100){
                                System.out.println("Dribble should be between 0 and 100.");
                                continue;
                            }
                            double passing = Double.parseDouble(tokens[6]);
                            if(passing < 0 || passing > 100){
                                System.out.println("Passing should be between 0 and 100.");
                                continue;
                            }
                            double shooting = Double.parseDouble(tokens[7]);
                            if(shooting < 0 || shooting > 100){
                                System.out.println("Shooting should be between 0 and 100.");
                                continue;
                            }
                            Player player = new Player(tokens[2], endurance, sprint, dribble, passing, shooting);
                            teams.get(tokens[1]).addPlayer(tokens[2], player);
                        } else {
                            System.out.println(String.format("Team %s does not exist.", tokens[1]));
                        }
                        break;
                    case "Remove":
                        if (teams.get(tokens[1]).hasPlayer(tokens[2])) {
                            teams.get(tokens[1]).removePlayer(tokens[2]);
                        } else {
                            System.out.println(String.format("Player %s is not in %s team.", tokens[2], tokens[1]));
                        }
                        break;
                    case "Rating":
                        if (teams.containsKey(tokens[1])) {
                            System.out.println(String.format("%s - %d", tokens[1], (int) Math.round(teams.get(tokens[1]).calculateRating())));
                        } else {
                            System.out.println(String.format("Team %s does not exist.", tokens[1]));
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
