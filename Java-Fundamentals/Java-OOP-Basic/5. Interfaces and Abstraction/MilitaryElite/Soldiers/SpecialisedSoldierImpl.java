package MilitaryElite.Soldiers;

import MilitaryElite.Interfaces.SpecialisedSoldier;

public abstract class SpecialisedSoldierImpl extends PrivateImpl implements SpecialisedSoldier {
    private String corps;

    public SpecialisedSoldierImpl(String firstName, String lastName, int id, double salary, String corps) {
        super(firstName, lastName, id, salary);
        this.setCorps(corps);
    }

    private void setCorps(String corps) {
        this.corps = corps;
    }

    @Override
    public String getCorps() {
        return this.corps;
    }
}
