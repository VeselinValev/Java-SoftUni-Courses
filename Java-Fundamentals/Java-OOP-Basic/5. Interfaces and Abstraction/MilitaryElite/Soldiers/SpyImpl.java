package MilitaryElite.Soldiers;

import MilitaryElite.Interfaces.Spy;

public class SpyImpl extends SoldierImpl implements Spy {
    private String codeNumber;

    public SpyImpl(String firstName, String lastName, int id, String codeNumber) {
        super(firstName, lastName, id);
        this.setCodeNumber(codeNumber);
    }

    @Override
    public String getCodeNumber() {
        return this.codeNumber;
    }

    @Override
    public String toString(){
        return String.format("Name: %s %s Id: %S\nCode Number: %s", this.getFirstName(), this.getLastName(), this.getId(), this.getCodeNumber());
    }

    private void setCodeNumber(String codeNumber) {
        this.codeNumber = codeNumber;
    }
}
