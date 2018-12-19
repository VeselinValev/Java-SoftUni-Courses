package app.waste_disposal;

import app.waste_disposal.contracts.ManagementRequirements;

public class DefaultManagementRequirements implements ManagementRequirements {
    private double energyRequirement;
    private double capitalRequirement;
    private String deniedProcess;

    public DefaultManagementRequirements(double energyRequirement, double capitalRequirement, String deniedProcess) {
        this.energyRequirement = energyRequirement;
        this.capitalRequirement = capitalRequirement;
        this.deniedProcess = deniedProcess;
    }

    @Override
    public double getEnergyRequirement() {
        return this.energyRequirement;
    }

    @Override
    public double getCapitalRequirement() {
        return this.capitalRequirement;
    }

    @Override
    public String getDeniedProcess() {
        return this.deniedProcess;
    }
}
