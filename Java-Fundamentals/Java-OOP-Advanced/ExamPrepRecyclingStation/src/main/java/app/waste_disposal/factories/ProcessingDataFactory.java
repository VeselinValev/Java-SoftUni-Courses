package app.waste_disposal.factories;

import app.waste_disposal.contracts.ProcessingData;
import app.waste_disposal.models.processes.ProcessingDataImpl;

public class ProcessingDataFactory {

    public static ProcessingData createProcessingData(double energy, double capital){
        return new ProcessingDataImpl(energy, capital);
    }
}
