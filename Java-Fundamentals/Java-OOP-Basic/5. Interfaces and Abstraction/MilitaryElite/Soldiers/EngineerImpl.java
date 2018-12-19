package MilitaryElite.Soldiers;

import MilitaryElite.Interfaces.Engineer;
import MilitaryElite.Interfaces.Repair;

import java.util.ArrayList;
import java.util.List;

public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer {

    private List<Repair> repairs;

    public EngineerImpl(String firstName, String lastName, int id, double salary, String corps, List<Repair> repairs) {
        super(firstName, lastName, id, salary, corps);
        this.setRepairs(repairs);
    }

    @Override
    public List<Repair> getRepairs() {
        return this.repairs;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        String toAppend = String.format("Name: %s %s Id: %S Salary: %.2f%n", this.getFirstName(), this.getLastName(), this.getId(), this.getSalary());
        sb.append(toAppend).append(String.format("Corps: %s%n", this.getCorps()));
        sb.append("Repairs:");
        for (Repair repair : repairs){
            sb.append("\n").append("  ").append(repair.toString());
        }
        return sb.toString();
    }
    private void setRepairs(List<Repair> repairs) {
        this.repairs = new ArrayList<>(repairs);
    }
}
