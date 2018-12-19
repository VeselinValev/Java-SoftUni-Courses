package app.waste_disposal.models.disposalStrategies;

import app.waste_disposal.contracts.GarbageDisposalStrategy;
import app.waste_disposal.contracts.ProcessingData;
import app.waste_disposal.contracts.Waste;
import app.waste_disposal.factories.ProcessingDataFactory;

public class BurnGarbageStrategy implements GarbageDisposalStrategy {

    @Override
    public ProcessingData processGarbage(Waste garbage) {
        double totalGarbageVolume = garbage.getWeight() * garbage.getVolumePerKg();
        double totalEnergyProduced = totalGarbageVolume * 0.8;
        return ProcessingDataFactory.createProcessingData(totalEnergyProduced, 0);
    }
}
