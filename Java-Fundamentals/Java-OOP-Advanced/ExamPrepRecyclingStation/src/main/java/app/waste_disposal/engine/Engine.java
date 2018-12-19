package app.waste_disposal.engine;

import app.waste_disposal.contracts.*;
import app.waste_disposal.contracts.Runnable;
import app.waste_disposal.factories.ManagementRequirementsFactory;
import app.waste_disposal.factories.WasteFactory;

import java.io.IOException;

public class Engine implements Runnable {

    private GarbageProcessor garbageProcessor;
    private double totalEnergy;
    private double totalCapital;
    private Reader reader;
    private Writer writer;
    private ManagementRequirements managementRequirements;

    public Engine(GarbageProcessor garbageProcessor, Reader reader, Writer writer, ManagementRequirements managementRequirements) {
        this.garbageProcessor = garbageProcessor;
        this.reader = reader;
        this.writer = writer;
        this.managementRequirements = managementRequirements;
    }

    @Override
    public void run() throws IOException {
        for (String input = reader.readLine(); !input.equals("TimeToRecycle"); input = reader.readLine()) {
            String command = input.split(" ")[0];
            if (command.equals("ProcessGarbage")) {
                String[] data = input.split(" ")[1].split("[| ]");
                String garbageName = data[0];
                double garbageWeight = Double.valueOf(data[1]);
                double garbageVolumePerKg = Double.valueOf(data[2]);
                String garbageType = data[3];
                Waste currentGarbage = WasteFactory.createWaste(garbageName, garbageVolumePerKg, garbageWeight, garbageType);
                if (validateProcess(this.managementRequirements, this.totalEnergy, this.totalCapital, garbageType)) {
                    try {
                        ProcessingData processingData = this.garbageProcessor.processWaste(currentGarbage);
                        this.totalEnergy += processingData.getEnergyBalance();
                        this.totalCapital += processingData.getCapitalBalance();
                        writer.writeLine(String.format("%.2f kg of %s successfully processed!", garbageWeight, garbageName));
                    } catch (IllegalArgumentException iae) {
                        writer.writeLine(iae.getMessage());
                    }
                } else {
                    writer.writeLine("Processing Denied!");
                }

            } else if (command.equals("ChangeManagementRequirement")) {
                String[] data = input.split(" ")[1].split("\\|");
                this.managementRequirements = ManagementRequirementsFactory.createManagementRequirements(Double.parseDouble(data[0]),
                        Double.parseDouble(data[1]), data[2]);
                writer.writeLine("Management requirement changed!");
            } else {
                this.writer.writeLine(String.format("Energy: %.2f Capital: %.2f", this.totalEnergy, this.totalCapital));
            }
        }
    }

    private boolean validateProcess(ManagementRequirements managementRequirements, double totalEnergy, double totalCapital, String garbageType){
        boolean result = true;
        if (managementRequirements.getDeniedProcess().equals(garbageType)) {
            if(managementRequirements.getCapitalRequirement() > totalCapital ||
                    managementRequirements.getEnergyRequirement() > totalEnergy){
                result = false;
            }
        }
        return result;
    }
}
