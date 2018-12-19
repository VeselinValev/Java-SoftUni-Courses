package MilitaryElite.Soldiers;

import MilitaryElite.Interfaces.Private;

public class PrivateImpl extends SoldierImpl implements Private {
    private double salary;

    public PrivateImpl(String firstName, String lastName, int id, double salary) {
        super(firstName, lastName, id);
        this.setSalary(salary);
    }

    @Override
    public String toString(){
        return String.format("Name: %s %s Id: %s Salary: %.2f", this.getFirstName(), this.getLastName(), this.getId(), this.getSalary());
    }

    @Override
    public double getSalary() {
        return this.salary;
    }

    private void setSalary(double salary) {
        this.salary = salary;
    }
}
