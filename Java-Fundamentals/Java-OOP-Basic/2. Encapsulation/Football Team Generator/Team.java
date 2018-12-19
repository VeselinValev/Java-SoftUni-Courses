package exerciseOOP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Team {
    private String name;
    private Map<String, Player> players;

    public Team(String name) {
        this.setName(name);
        this.players = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    public double calculateRating() {
        if(players.isEmpty()){
            return 0;
        }

        double result = 0;
        for (Map.Entry<String, Player> entry: players.entrySet()) {
            result += entry.getValue().getSkillLevel();
        }
        result /= players.size();

        return result;
    }

    public void addPlayer(String name, Player player){
        this.players.put(name, player);
    }

    public boolean hasPlayer(String name){

        return players.containsKey(name);
    }

    public void removePlayer(String name){

        this.players.remove(name);


    }
}
