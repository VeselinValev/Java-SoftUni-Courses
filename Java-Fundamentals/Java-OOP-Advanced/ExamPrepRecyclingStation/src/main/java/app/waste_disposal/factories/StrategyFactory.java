package app.waste_disposal.factories;

import app.waste_disposal.contracts.GarbageDisposalStrategy;
import app.waste_disposal.models.disposalStrategies.BurnGarbageStrategy;
import app.waste_disposal.models.disposalStrategies.RecycleGarbageStrategy;
import app.waste_disposal.models.disposalStrategies.StoreGarbageStrategy;

public class StrategyFactory {

    public static GarbageDisposalStrategy createBurnGarbageStrategy(){
        return new BurnGarbageStrategy();
    }

    public static GarbageDisposalStrategy createRecycleGarbageStrategy(){
        return new RecycleGarbageStrategy();
    }

    public static GarbageDisposalStrategy createStoreGarbageStrategy(){
        return new StoreGarbageStrategy();
    }

}
