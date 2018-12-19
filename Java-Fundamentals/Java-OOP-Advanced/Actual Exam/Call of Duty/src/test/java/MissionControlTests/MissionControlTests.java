package MissionControlTests;

import callofduty.core.MissionControlImpl;
import callofduty.interfaces.Mission;
import callofduty.interfaces.MissionControl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class MissionControlTests {

    private MissionControl missionControl;

    @Before
    public void before(){
        this.missionControl = new MissionControlImpl();
    }

    @Test
    public void testValidations() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Mission mission = this.missionControl.generateMission("0000000000", -10.0, -10.0);
        Assert.assertEquals("00000000", mission.getId());
        Assert.assertEquals(0.0, mission.getRating(), 0.1);
        Assert.assertEquals(0.0, mission.getBounty(), 0.1);
    }

    @Test
    public void testValidations2() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Mission mission = this.missionControl.generateMission("0000000000", 1000000.0, 1000000.0);
        Assert.assertEquals(75.0, mission.getRating(), 0.1);
        Assert.assertEquals(125000.0, mission.getBounty(), 0.1);
    }

    @Test
    public void testIterationOverMissionTypes() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Mission mission1 = this.missionControl.generateMission("01", 1000000.0, 1000000.0);
        Mission mission2 = this.missionControl.generateMission("02", 1000000.0, 1000000.0);
        Mission mission3 = this.missionControl.generateMission("03", 1000000.0, 1000000.0);
        Mission mission4 = this.missionControl.generateMission("04", 1000000.0, 1000000.0);

        Assert.assertEquals("EscortMission", mission1.getClass().getSimpleName());
        Assert.assertEquals("HuntMission", mission2.getClass().getSimpleName());
        Assert.assertEquals("SurveillanceMission", mission3.getClass().getSimpleName());
        Assert.assertEquals("EscortMission", mission4.getClass().getSimpleName());

    }

}
