package MilitaryElite.Soldiers;

import MilitaryElite.Interfaces.Mission;

public class MissionImpl implements Mission {
    private String missionState;
    private String missionName;

    public MissionImpl(String missionName, String missionState) {
        this.setMissionName(missionName);
        this.setMissionState(missionState);
    }

    @Override
    public String getMissionState() {
        return this.missionState;
    }

    @Override
    public String getMissionName() {
        return this.missionName;
    }

    @Override
    public String toString(){
        return String.format("Code Name: %s State: %s", this.getMissionName(), this.getMissionState());
    }

    private void setMissionState(String missionState) {
        this.missionState = missionState;
    }

    private void setMissionName(String missionName) {
        this.missionName = missionName;
    }
}
