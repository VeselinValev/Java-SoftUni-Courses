package app.waste_disposal;

import app.waste_disposal.annotations.Burnable;
import app.waste_disposal.annotations.Recyclable;
import app.waste_disposal.annotations.Storable;
import app.waste_disposal.contracts.GarbageDisposalStrategy;
import app.waste_disposal.contracts.StrategyHolder;
import app.waste_disposal.factories.StrategyFactory;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultStrategyHolder implements StrategyHolder {

    private Map<Class, GarbageDisposalStrategy> strategies;

    public DefaultStrategyHolder(){
        this.setStrategies();

    }

    private void setStrategies() {
        Map<Class,GarbageDisposalStrategy> strategies = new LinkedHashMap<>();
        strategies.put(Burnable.class, StrategyFactory.createBurnGarbageStrategy());
        strategies.put(Recyclable.class, StrategyFactory.createRecycleGarbageStrategy());
        strategies.put(Storable.class, StrategyFactory.createStoreGarbageStrategy());
        this.strategies = strategies;
    }

    @Override
    public Map<Class, GarbageDisposalStrategy> getDisposalStrategies() {
        return Collections.unmodifiableMap(this.strategies);
    }

    @Override
    public boolean addStrategy(Class annotationClass, GarbageDisposalStrategy strategy) {
        this.strategies.put(annotationClass, strategy);
        return true;
    }

    @Override
    public boolean removeStrategy(Class annotationClass) {
        this.strategies.remove(annotationClass);
        return true;
    }
}
