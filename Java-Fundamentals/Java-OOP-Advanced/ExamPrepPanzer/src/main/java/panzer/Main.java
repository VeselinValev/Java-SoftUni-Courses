package panzer;

import panzer.contracts.*;
import panzer.contracts.Runnable;
import panzer.core.ManagerImpl;
import panzer.core.PanzerBattleOperator;
import panzer.engine.Engine;
import panzer.io.ConsoleReaderImpl;
import panzer.io.ConsoleWriterImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        InputReader reader = new ConsoleReaderImpl();
        OutputWriter writer = new ConsoleWriterImpl();
        BattleOperator battleOperator = new PanzerBattleOperator();
        Manager manager = new ManagerImpl(battleOperator);
        Runnable engine = new Engine(reader, writer, manager);
        engine.run();
    }
}
