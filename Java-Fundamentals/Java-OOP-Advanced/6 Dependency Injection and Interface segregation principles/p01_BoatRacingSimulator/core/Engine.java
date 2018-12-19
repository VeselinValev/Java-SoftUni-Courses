package t07_InterfaceSegregationDependencyInversion.p01_BoatRacingSimulator.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine {
    private CommandHandlerImpl commandHandler;
    private StringBuilder result;

    public Engine(CommandHandlerImpl commandHandler, StringBuilder result) {
        this.commandHandler = commandHandler;
        this.result = result;
    }

    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (String line = reader.readLine(); !line.equals("End"); line = reader.readLine()) {
            String[] tokens = line.split("\\\\");
            try {
                this.commandHandler.executeCommand(tokens);
            } catch (Exception ex) {
                result.append(ex.getMessage()).append(System.lineSeparator());
            }
        }
        System.out.print(result.toString().trim());
    }
}
