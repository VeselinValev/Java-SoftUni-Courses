package MilitaryElite.Soldiers;

import MilitaryElite.Interfaces.Soldier;

public abstract class SoldierImpl implements Soldier {
    private String firstName;
    private String lastName;
    private int id;

    public SoldierImpl(String firstName, String lastName, int id) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setId(id);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private void setId(int id) {
        this.id = id;
    }
}
