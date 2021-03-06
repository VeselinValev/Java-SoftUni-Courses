package MilitaryElite.Soldiers;

import MilitaryElite.Interfaces.Repair;

public class RepairImpl implements Repair {
    private String partName;
    private int hoursWorked;

    public RepairImpl(String partName, int hoursWorked) {
        this.setPartName(partName);
        this.setHoursWorked(hoursWorked);
    }

    @Override
    public String getPartName() {
        return this.partName;
    }

    @Override
    public int getHoursWorked() {
        return this.hoursWorked;
    }

    @Override
    public String toString(){
        return String.format("Part Name: %s Hours Worked: %d", this.getPartName(), this.getHoursWorked());
    }

    private void setPartName(String partName) {
        this.partName = partName;
    }

    private void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
}
