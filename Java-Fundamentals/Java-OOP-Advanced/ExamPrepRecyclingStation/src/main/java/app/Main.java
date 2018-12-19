package app;

import app.waste_disposal.DefaultGarbageProcessor;
import app.waste_disposal.DefaultStrategyHolder;
import app.waste_disposal.contracts.*;
import app.waste_disposal.contracts.Runnable;
import app.waste_disposal.engine.Engine;
import app.waste_disposal.factories.ManagementRequirementsFactory;
import app.waste_disposal.io.ConsoleReaderImpl;
import app.waste_disposal.io.ConsoleWriterImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader reader = new ConsoleReaderImpl();
        Writer writer = new ConsoleWriterImpl();
        StrategyHolder strategyHolder = new DefaultStrategyHolder();
        GarbageProcessor garbageProcessor = new DefaultGarbageProcessor(strategyHolder);
        ManagementRequirements managementRequirements = ManagementRequirementsFactory.createManagementRequirements(0,0, "None");
        Runnable engine = new Engine(garbageProcessor, reader, writer, managementRequirements);
        engine.run();
    }
}
