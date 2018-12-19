package callofduty.factories;

import callofduty.domain.agents.MasterAgent;
import callofduty.domain.agents.NoviceAgent;
import callofduty.interfaces.Agent;

import java.lang.reflect.Field;

public class AgentFactory {

    public static Agent createNoviceAgent(String id, String name){
        return new NoviceAgent(id, name, 0);
    }

    public static Agent createMasterAgent(Agent noviceAgent) throws IllegalAccessException {

        Agent masterAgent = new MasterAgent(noviceAgent.getId(), noviceAgent.getName(), noviceAgent.getRating());

        Field noviceMissionsToComplete = noviceAgent.getClass().getSuperclass().getDeclaredFields()[3];
        noviceMissionsToComplete.setAccessible(true);
        Field noviceCompletedMissions = noviceAgent.getClass().getSuperclass().getDeclaredFields()[4];
        noviceCompletedMissions.setAccessible(true);

        Field masterMissionsToComplete = masterAgent.getClass().getSuperclass().getDeclaredFields()[3];
        masterMissionsToComplete.setAccessible(true);
        Field masterCompletedMissions = masterAgent.getClass().getSuperclass().getDeclaredFields()[4];
        masterCompletedMissions.setAccessible(true);

        masterMissionsToComplete.set(masterAgent, noviceMissionsToComplete.get(noviceAgent));
        masterCompletedMissions.set(masterAgent, noviceCompletedMissions.get(noviceAgent));

        return masterAgent;
    }
}
