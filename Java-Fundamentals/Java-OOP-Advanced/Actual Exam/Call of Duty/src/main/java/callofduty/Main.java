package callofduty;

import callofduty.core.MissionControlImpl;
import callofduty.core.MissionManagerImpl;
import callofduty.engine.Engine;
import callofduty.interfaces.InputReader;
import callofduty.interfaces.MissionControl;
import callofduty.interfaces.MissionManager;
import callofduty.interfaces.OutputWriter;
import callofduty.interfaces.Runnable;
import callofduty.io.InputReaderImpl;
import callofduty.io.OutputWriterImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, IOException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        InputReader reader = new InputReaderImpl();
        OutputWriter writer = new OutputWriterImpl();
        MissionControl missionControl = new MissionControlImpl();
        MissionManager missionManager = new MissionManagerImpl(missionControl);
        Runnable engine = new Engine(reader, writer, missionManager);
        engine.run();
    }
}




