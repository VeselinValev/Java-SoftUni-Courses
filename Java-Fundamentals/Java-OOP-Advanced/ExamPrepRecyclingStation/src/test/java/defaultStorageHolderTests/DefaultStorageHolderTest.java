package defaultStorageHolderTests;

import app.waste_disposal.DefaultStrategyHolder;
import app.waste_disposal.annotations.Burnable;
import app.waste_disposal.contracts.GarbageDisposalStrategy;
import app.waste_disposal.contracts.StrategyHolder;
import app.waste_disposal.models.disposalStrategies.BurnGarbageStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class DefaultStorageHolderTest {
    private StrategyHolder strategyHolder;
    private GarbageDisposalStrategy strategy;


    @Before
    public void before(){
        this.strategyHolder = Mockito.mock(DefaultStrategyHolder.class);
        this.strategy = Mockito.mock(BurnGarbageStrategy.class);
    }

    @Test
    public void testAddAndRemoveStrategyMethod(){
        this.strategyHolder.addStrategy(Burnable.class, this.strategy);
        Assert.assertEquals(1, this.strategyHolder.getDisposalStrategies().size());
        this.strategyHolder.removeStrategy(Burnable.class);
        Assert.assertEquals(0, this.strategyHolder.getDisposalStrategies().size());
    }
}
