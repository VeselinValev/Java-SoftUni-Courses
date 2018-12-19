package hell;

import hell.controllers.HeroController;
import hell.engine.Engine;
import hell.interfaces.Controller;
import hell.interfaces.InputReader;
import hell.interfaces.OutputWriter;
import hell.interfaces.Runnable;
import hell.io.ConsoleReader;
import hell.io.ConsoleWriter;

public class Main {
    public static void main(String[] args) {
        InputReader reader = new ConsoleReader();
        OutputWriter writer = new ConsoleWriter();
        Controller controller = new HeroController();
        Runnable engine = new Engine(reader, writer, controller);
        engine.run();
    }
}