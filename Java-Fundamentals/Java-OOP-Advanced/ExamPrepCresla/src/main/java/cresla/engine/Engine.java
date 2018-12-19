package cresla.engine;

import cresla.interfaces.InputReader;
import cresla.interfaces.Manager;
import cresla.interfaces.OutputWriter;

import java.util.Arrays;
import java.util.List;

public class Engine implements Runnable {
    private InputReader reader;
    private OutputWriter writer;
    private Manager manager;

    public Engine(InputReader reader, OutputWriter writer, Manager manager) {
        this.reader = reader;
        this.writer = writer;
        this.manager = manager;
    }

    @Override
    public void run() {
        while (true){
            String[] input = reader.readLine().split(" ");
            List<String> arguments = Arrays.asList(input);
            if (input[0].equals("Reactor")){
                writer.writeLine(manager.reactorCommand(arguments));
            }else if(input[0].equals("Module")){
                writer.writeLine(manager.moduleCommand(arguments));
            }else if(input[0].equals("Report")){
                writer.writeLine(manager.reportCommand(arguments));
            }else if(input[0].equals("Exit")){
                writer.writeLine(manager.exitCommand(arguments));
                break;
            }
        }
    }
}
