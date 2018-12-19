package callofduty.core;

import callofduty.interfaces.Mission;
import callofduty.interfaces.MissionControl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MissionControlImpl implements MissionControl {
    private static final Integer MISSION_ID_MAXIMUM_LENGTH = 8;

    private static final Double MISSION_RATING_MINIMUM_VALUE = 0D;

    private static final Double MISSION_RATING_MAXIMUM_VALUE = 100D;

    private static final Double MISSION_BOUNTY_MINIMUM_VALUE = 0D;

    private static final Double MISSION_BOUNTY_MAXIMUM_VALUE = 100000D;

    private Map<String, Class> missionClasses;

    private int missionIndex;

    private Iterator<Map.Entry<String, Class>> missionIterator;

    public MissionControlImpl() {
        this.initMissionClasses();
        this.missionIterator = this.missionClasses
                .entrySet()
                .iterator();
        this.missionIndex = 0;
    }

    private void initMissionClasses() {
        try {
            this.missionClasses = new LinkedHashMap<>() {{
                put("EscortMission", Class.forName("callofduty.domain.missions.EscortMission"));
                put("HuntMission", Class.forName("callofduty.domain.missions.HuntMission"));
                put("SurveillanceMission", Class.forName("callofduty.domain.missions.SurveillanceMission"));
            }};
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String checkAndReformMissionId(String missionId) {
        if (missionId.length() > MISSION_ID_MAXIMUM_LENGTH){
            return missionId.substring(0, MISSION_ID_MAXIMUM_LENGTH);
        }
        return missionId;
    }

    private Double checkAndReformMissionRating(Double missionRating) {
        double reformedRating = missionRating;
        if (missionRating < MISSION_RATING_MINIMUM_VALUE){
            reformedRating = MISSION_RATING_MINIMUM_VALUE;
        }
        if (missionRating > MISSION_RATING_MAXIMUM_VALUE){
            reformedRating = MISSION_RATING_MAXIMUM_VALUE;
        }
        return reformedRating;
    }

    private Double checkAndreformMissionBounty(Double missionBounty) {
            double reformedBounty = missionBounty;
            if (missionBounty < MISSION_BOUNTY_MINIMUM_VALUE){
                reformedBounty = MISSION_BOUNTY_MINIMUM_VALUE;
            }
            if (missionBounty > MISSION_BOUNTY_MAXIMUM_VALUE){
                reformedBounty = MISSION_BOUNTY_MAXIMUM_VALUE;
            }
        return reformedBounty;
    }

    @Override
    public Mission generateMission(String missionId, Double missionRating, Double missionBounty) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        missionId = this.checkAndReformMissionId(missionId);
        missionRating = this.checkAndReformMissionRating(missionRating);
        missionBounty = this.checkAndreformMissionBounty(missionBounty);

        Class<?> missionClass = null;
        int counter = 0;
        for (Class currentClass: this.missionClasses.values()){
            if (counter++ == this.missionIndex){
                missionClass = currentClass;
                this.missionIndex++;
                if (this.missionIndex > 2){
                    this.missionIndex = 0;
                }
                break;
            }

        }
        Constructor<?> constructor = missionClass.getDeclaredConstructor(String.class, Double.class, Double.class);
        constructor.setAccessible(true);
        Mission missionObject = (Mission) constructor.newInstance(missionId, missionRating, missionBounty);

        return missionObject;

    }
}