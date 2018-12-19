package callofduty.core;

import callofduty.domain.agents.NoviceAgent;
import callofduty.domain.missions.EscortMission;
import callofduty.domain.missions.HuntMission;
import callofduty.domain.missions.SurveillanceMission;
import callofduty.factories.AgentFactory;
import callofduty.interfaces.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MissionManagerImpl implements MissionManager {

    private MissionControl missionControl;
    private Map<String, Agent> allAgents;
    private Map<String, Mission> allMissions;

    public MissionManagerImpl(MissionControl missionControl) {
        this.missionControl = missionControl;
        this.allAgents = new LinkedHashMap<>();
        this.allMissions = new LinkedHashMap<>();
    }

    @Override
    public String agent(List<String> arguments) {
        Agent newAgent = AgentFactory.createNoviceAgent(arguments.get(1), arguments.get(2));
        this.allAgents.put(newAgent.getId(), newAgent);
        return String.format("Registered Agent - %s:%s", newAgent.getName(), newAgent.getId());
    }

    @Override
    public String request(List<String> arguments) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Double rating =  Double.valueOf(arguments.get(3));
        Double bounty =  Double.valueOf(arguments.get(4));
        Mission newMission = this.missionControl.generateMission(arguments.get(2), rating, bounty);
        this.allMissions.put(newMission.getId(), newMission);
        Agent currentAgent = this.allAgents.get(arguments.get(1));
        currentAgent.acceptMission(newMission);

        String missionType = "";
        if (newMission instanceof EscortMission){
            missionType = "Escort";
        }else if (newMission instanceof HuntMission){
            missionType = "Hunt";
        }else if (newMission instanceof SurveillanceMission){
            missionType = "Surveillance";
        }
        return String.format("Assigned %s Mission - %s to Agent - %s",
                missionType, newMission.getId(), currentAgent.getName());
    }

    @Override
    public String complete(List<String> arguments) throws IllegalAccessException, NoSuchFieldException {
        Agent currentAgent = this.allAgents.get(arguments.get(1));
        currentAgent.completeMissions();
        Field completedMissions = currentAgent.getClass().getSuperclass().getDeclaredFields()[4];
        completedMissions.setAccessible(true);
        if (currentAgent instanceof NoviceAgent){
            if (((Map<String, Mission>)completedMissions.get(currentAgent)).size() >= 3){
                Agent masterAgent = AgentFactory.createMasterAgent(currentAgent);
                this.allAgents.remove(currentAgent.getId());
                this.allAgents.put(masterAgent.getId(), masterAgent);
                currentAgent = masterAgent;
            }
        }
        return String.format("Agent - %s:%s has completed all assigned missions.", currentAgent.getName(), currentAgent.getId());
    }

    @Override
    public String status(List<String> arguments) {
        if (this.allAgents.containsKey(arguments.get(1))){
            return this.allAgents.get(arguments.get(1)).toString();
        }else{
            return this.allMissions.get(arguments.get(1)).toString();
        }
    }

    @Override
    public String over(List<String> arguments) throws NoSuchFieldException, IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        int noviceAgentsCount = 0;
        int masterAgentsCount = 0;
        double totalRatingEarned = 0.0;
        double totalBountyEarned = 0.0;
        int totalCompletedMissions = 0;
        for (Agent agent: this.allAgents.values()){
            totalRatingEarned += agent.getRating();

            Field completedMissions = agent.getClass().getSuperclass().getDeclaredField("completedMissions");
            completedMissions.setAccessible(true);
            totalCompletedMissions += ((Map<String, Mission>)completedMissions.get(agent)).size();

            if (agent instanceof NoviceAgent){
                noviceAgentsCount++;
            }else{
                masterAgentsCount++;
                Field bountyEarned = agent.getClass().getDeclaredField("bounty");
                bountyEarned.setAccessible(true);
                totalBountyEarned += (double)bountyEarned.get(agent);
            }
        }
        sb.append(String.format("Novice Agents: %s%n", noviceAgentsCount));
        sb.append(String.format("Master Agents: %s%n", masterAgentsCount));
        sb.append(String.format("Assigned Missions: %s%n", this.allMissions.size()));
        sb.append(String.format("Completed Missions: %s%n",totalCompletedMissions));
        sb.append(String.format("Total Rating Given: %.2f%n", totalRatingEarned));
        sb.append(String.format("Total Bounty Given: $%.2f", totalBountyEarned));

        return sb.toString();
    }
}
