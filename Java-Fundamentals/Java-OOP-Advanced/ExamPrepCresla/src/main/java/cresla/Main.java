package cresla;

import cresla.engine.Engine;
import cresla.interfaces.InputReader;
import cresla.interfaces.Manager;
import cresla.interfaces.OutputWriter;
import cresla.io.ConsoleReader;
import cresla.io.ConsoleWriter;

import java.lang.reflect.InvocationTargetException;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        InputReader reader = new ConsoleReader();
        OutputWriter writer = new ConsoleWriter();
        Manager manager = new ManagerImpl();
        Runnable engine = new Engine(reader, writer, manager);
        engine.run();
    }
}
