package MilitaryElite.Soldiers;

import MilitaryElite.Interfaces.Commando;
import MilitaryElite.Interfaces.Mission;

import java.util.ArrayList;
import java.util.List;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando {

    private List<Mission> missions;

    public CommandoImpl(String firstName, String lastName, int id, double salary, String corps, List<Mission> missions) {
        super(firstName, lastName, id, salary, corps);
        this.setMissions(missions);
    }

    @Override
    public List<Mission> getMissions() {
        return this.missions;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        String toAppend = String.format("Name: %s %s Id: %S Salary: %.2f%n", this.getFirstName(), this.getLastName(), this.getId(), this.getSalary());
        sb.append(toAppend).append(String.format("Corps: %s%n", this.getCorps()));
        sb.append("Missions:");
        for (Mission mission: missions){
            sb.append("\n").append("  ").append(mission.toString());
        }
        return sb.toString();
    }
    private void setMissions(List<Mission> missions) {
        this.missions = new ArrayList<>(missions);
    }
}
