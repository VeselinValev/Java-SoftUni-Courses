package AssemblerTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import panzer.contracts.Assembler;
import panzer.contracts.AttackModifyingPart;
import panzer.contracts.DefenseModifyingPart;
import panzer.contracts.HitPointsModifyingPart;
import panzer.models.miscellaneous.VehicleAssembler;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

public class AssemblerTests {
    private Assembler assembler;
    private AttackModifyingPart attackModifyingPart;
    private DefenseModifyingPart defenseModifyingPart;
    private HitPointsModifyingPart hitPointsModifyingPart;

    @Before
    public void before(){
        this.assembler = new VehicleAssembler();
        this.attackModifyingPart = Mockito.mock(AttackModifyingPart.class);
        this.defenseModifyingPart = Mockito.mock(DefenseModifyingPart.class);
        this.hitPointsModifyingPart = Mockito.mock(HitPointsModifyingPart.class);
    }

    @Test
    public void testTotalAttackModifier(){
        Mockito.when(this.attackModifyingPart.getAttackModifier()).thenReturn(2_000_000_000);
        this.assembler.addArsenalPart(this.attackModifyingPart);
        this.assembler.addArsenalPart(this.attackModifyingPart);
        Assert.assertEquals(4_000_000_000L, this.assembler.getTotalAttackModification());
    }

    @Test
    public void testTotalDefenceModifier(){
        Mockito.when(this.defenseModifyingPart.getDefenseModifier()).thenReturn(2_000_000_000);
        this.assembler.addShellPart(this.defenseModifyingPart);
        this.assembler.addShellPart(this.defenseModifyingPart);
        Assert.assertEquals(4_000_000_000L, this.assembler.getTotalDefenseModification());
    }

    @Test
    public void testTotalHitPointsModifier(){
        Mockito.when(this.hitPointsModifyingPart.getHitPointsModifier()).thenReturn(2_000_000_000);
        this.assembler.addEndurancePart(this.hitPointsModifyingPart);
        this.assembler.addEndurancePart(this.hitPointsModifyingPart);
        Assert.assertEquals(4_000_000_000L, this.assembler.getTotalHitPointModification());
    }

    @Test
    public void testGetTotalWeight(){
        Mockito.when(this.hitPointsModifyingPart.getWeight()).thenReturn(10.0);
        Mockito.when(this.attackModifyingPart.getWeight()).thenReturn(10.0);
        Mockito.when(this.defenseModifyingPart.getWeight()).thenReturn(10.0);
        this.assembler.addEndurancePart(this.hitPointsModifyingPart);
        this.assembler.addShellPart(this.defenseModifyingPart);
        this.assembler.addArsenalPart(this.attackModifyingPart);
        Assert.assertEquals(30.0, this.assembler.getTotalWeight(), 0.1);
    }

    @Test
    public void testGetTotalPrice(){
        Mockito.when(this.hitPointsModifyingPart.getPrice()).thenReturn(BigDecimal.valueOf(10));
        Mockito.when(this.attackModifyingPart.getPrice()).thenReturn(BigDecimal.valueOf(10));
        Mockito.when(this.defenseModifyingPart.getPrice()).thenReturn(BigDecimal.valueOf(10));
        this.assembler.addEndurancePart(this.hitPointsModifyingPart);
        this.assembler.addShellPart(this.defenseModifyingPart);
        this.assembler.addArsenalPart(this.attackModifyingPart);
        Assert.assertEquals(BigDecimal.valueOf(30), this.assembler.getTotalPrice());
    }

    @Test
    public void testAddPart() throws IllegalAccessException {
        this.assembler.addEndurancePart(this.hitPointsModifyingPart);
        this.assembler.addShellPart(this.defenseModifyingPart);
        this.assembler.addArsenalPart(this.attackModifyingPart);
        Class<?> assemblerClass = this.assembler.getClass();
        Field[] fields = assemblerClass.getDeclaredFields();
        for (Field field: fields){
            field.setAccessible(true);
        }
        Assert.assertEquals(1, ((List<AttackModifyingPart>) fields[0].get(this.assembler)).size());
        Assert.assertEquals(1, ((List<DefenseModifyingPart>) fields[1].get(this.assembler)).size());
        Assert.assertEquals(1, ((List<HitPointsModifyingPart>) fields[2].get(this.assembler)).size());
    }
}
