package ModuleContainerTests;

import cresla.entities.containers.ModuleContainer;
import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Container;
import cresla.interfaces.EnergyModule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.NoSuchElementException;

public class ModuleContainerTests {
    private EnergyModule energyModule1;
    private AbsorbingModule absorbingModule1;
    private EnergyModule energyModule2;
    private AbsorbingModule absorbingModule2;
    private Container moduleContainer;

    @Before
    public void before(){
        this.moduleContainer = new ModuleContainer(2);
        this.energyModule1 = Mockito.mock(EnergyModule.class);
        this.absorbingModule1 = Mockito.mock(AbsorbingModule.class);
        this.energyModule2 = Mockito.mock(EnergyModule.class);
        this.absorbingModule2 = Mockito.mock(AbsorbingModule.class);
        Mockito.when(this.absorbingModule1.getHeatAbsorbing()).thenReturn(2_000_000_000);
        Mockito.when(this.absorbingModule2.getHeatAbsorbing()).thenReturn(2_000_000_000);
        Mockito.when(this.energyModule1.getEnergyOutput()).thenReturn(2_000_000_000);
        Mockito.when(this.energyModule2.getEnergyOutput()).thenReturn(2_000_000_000);

        Mockito.when(this.absorbingModule1.getId()).thenReturn(1);
        Mockito.when(this.absorbingModule2.getId()).thenReturn(2);
        Mockito.when(this.energyModule1.getId()).thenReturn(3);
        Mockito.when(this.energyModule2.getId()).thenReturn(4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEnergyModuleExceptionTest(){
        this.moduleContainer.addEnergyModule(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAbsorbingModuleExceptionTest(){
        this.moduleContainer.addAbsorbingModule(null);
    }

    @Test
    public void checkSumEnergyOutput(){
        this.moduleContainer.addEnergyModule(this.energyModule1);
        this.moduleContainer.addEnergyModule(this.energyModule2);
        Assert.assertEquals(4_000_000_000L, this.moduleContainer.getTotalEnergyOutput());
    }

    @Test
    public void checkSumHeatAbsorbing(){
        this.moduleContainer.addAbsorbingModule(this.absorbingModule1);
        this.moduleContainer.addAbsorbingModule(this.absorbingModule2);
        Assert.assertEquals( 4_000_000_000L, this.moduleContainer.getTotalHeatAbsorbing());
    }

    @Test
    public void removeLastModule(){
        this.moduleContainer.addEnergyModule(this.energyModule2);
        this.moduleContainer.addAbsorbingModule(this.absorbingModule1);
        this.moduleContainer.addAbsorbingModule(this.absorbingModule2);
        Assert.assertEquals( 4_000_000_000L, this.moduleContainer.getTotalHeatAbsorbing());
        Assert.assertEquals( 0, this.moduleContainer.getTotalEnergyOutput());
    }

    @Test(expected = NoSuchElementException.class)
    public void addModuleToContainerSize0(){
        Container container = new ModuleContainer(0);
        container.addAbsorbingModule(this.absorbingModule1);
    }

}
