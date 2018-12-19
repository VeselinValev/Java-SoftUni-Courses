package MilitaryElite.Soldiers;

import MilitaryElite.Interfaces.LeutenantGeneral;
import MilitaryElite.Interfaces.Private;
import MilitaryElite.Interfaces.Soldier;

import java.util.ArrayList;
import java.util.List;

public class LeutenantGeneralImpl extends PrivateImpl implements LeutenantGeneral {

    private List<Private> listOfPrivates;

    public LeutenantGeneralImpl(String firstName, String lastName, int id, double salary) {
        super(firstName, lastName, id, salary);
        this.setListOfPrivates();
    }

    @Override
    public List<Private> getSetOfPrivates() {
        return this.listOfPrivates;
    }

    public void addPrivate(Private privateToAdd){
        this.listOfPrivates.add(privateToAdd);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sortPrivates();
        String toAppend = String.format("Name: %s %s Id: %S Salary: %.2f%n", this.getFirstName(), this.getLastName(), this.getId(), this.getSalary());
        sb.append(toAppend);
        sb.append("Privates:");
        for (Private currentPrivate: listOfPrivates){
            sb.append("\n").append("  ").append(currentPrivate.toString());
        }
        return sb.toString();
    }

    private void setListOfPrivates() {
        this.listOfPrivates = new ArrayList<>();
    }

    private void sortPrivates(){
        this.listOfPrivates.sort((x,y) -> Integer.compare(y.getId(), x.getId()));
    }
}
