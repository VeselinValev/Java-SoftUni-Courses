package callofduty.domain.agents;

import callofduty.interfaces.Agent;
import callofduty.interfaces.Mission;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractAgent implements Agent {
    private String id;
    private String name;
    private double rating;
    private Map<String, Mission> missionsToComplete;
    private Map<String, Mission> completedMissions;

    protected AbstractAgent(String id, String name, double rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.missionsToComplete = new LinkedHashMap<>();
        this.completedMissions = new LinkedHashMap<>();
    }

    @Override
    public void acceptMission(Mission mission) {
        this.missionsToComplete.putIfAbsent(mission.getId(), mission);
    }

    @Override
    public void completeMissions() throws IllegalAccessException, NoSuchFieldException {
        for (Mission mission: this.missionsToComplete.values()){
            Field isCompleted = mission.getClass().getSuperclass().getDeclaredField("isCompleted");
            isCompleted.setAccessible(true);
            isCompleted.set(mission, true);
            this.rating += mission.getRating();
            if (this instanceof MasterAgent){
                Field bountyEarned = this.getClass().getDeclaredField("bounty");
                bountyEarned.setAccessible(true);
                bountyEarned.set(this, (double)bountyEarned.get(this) + mission.getBounty());
            }

            this.completedMissions.put(mission.getId(), mission);
        }
        this.missionsToComplete.clear();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getRating() {
        return this.rating;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Personal Code: %s%n", this.id));
        sb.append(String.format("Assigned Missions: %s%n", this.missionsToComplete.size()));
        sb.append(String.format("Completed Missions: %s%n", this.completedMissions.size()));
        sb.append(String.format("Rating: %.2f", this.rating));
        return sb.toString();
    }
}
