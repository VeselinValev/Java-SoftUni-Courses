package app.waste_disposal.factories;

import app.waste_disposal.DefaultManagementRequirements;
import app.waste_disposal.contracts.ManagementRequirements;

public class ManagementRequirementsFactory {

    public static ManagementRequirements createManagementRequirements(double energy, double capital, String deniedProcess){
        return new DefaultManagementRequirements(energy, capital, deniedProcess);
    }
}
