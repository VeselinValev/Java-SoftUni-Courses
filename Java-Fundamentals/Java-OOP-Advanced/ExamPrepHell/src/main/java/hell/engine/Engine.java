package hell.engine;

import hell.interfaces.*;
import hell.interfaces.Runnable;

public class Engine implements Runnable {
    private InputReader reader;
    private OutputWriter writer;
    private Controller controller;


    public Engine(InputReader reader, OutputWriter writer, Controller controller) {
        this.reader = reader;
        this.writer = writer;
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true) {
            String[] input = reader.readLine().split(" ");
            if (input[0].equals("Hero")) {
                this.writer.writeLine(this.controller.createHero(input[1], input[2]));
            } else if (input[0].equals("Item")) {
                this.writer.writeLine(this.controller.createCommonItem(input));
            } else if (input[0].equals("Recipe")) {
                this.writer.writeLine(this.controller.createRecipeItem(input));
            } else if (input[0].equals("Inspect")) {
                this.writer.writeLine(this.controller.inspect(input[1]));
            } else if (input[0].equals("Quit")) {
                this.writer.writeLine(this.controller.quit());
               break;
            }
        }
    }
}
