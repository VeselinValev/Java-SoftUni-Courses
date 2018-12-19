package app.waste_disposal.models.disposalStrategies;

import app.waste_disposal.contracts.GarbageDisposalStrategy;
import app.waste_disposal.contracts.ProcessingData;
import app.waste_disposal.contracts.Waste;
import app.waste_disposal.factories.ProcessingDataFactory;

public class StoreGarbageStrategy implements GarbageDisposalStrategy {
    @Override
    public ProcessingData processGarbage(Waste garbage) {
        double totalGarbageVolume = garbage.getWeight() * garbage.getVolumePerKg();
        double totalEnergyProduced = (totalGarbageVolume * 0.13) * -1;
        double capitalEarned = (totalGarbageVolume * 0.65) * -1;
        return ProcessingDataFactory.createProcessingData(totalEnergyProduced, capitalEarned);
    }
}
