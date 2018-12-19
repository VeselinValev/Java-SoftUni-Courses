package panzer.engine;

import panzer.contracts.*;
import panzer.contracts.Runnable;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public void run() throws IOException {
        while (true){
            List<String> data = Arrays.stream(this.reader.readLine().split(" ")).collect(Collectors.toList());
            if (data.get(0).equals("Vehicle")){
                this.writer.println(this.manager.addVehicle(data));
            }else if (data.get(0).equals("Part")){
                this.writer.println(this.manager.addPart(data));
            }else if (data.get(0).equals("Battle")){
                this.writer.println(this.manager.battle(data));
            } else if (data.get(0).equals("Inspect")){
                this.writer.println(this.manager.inspect(data));
            }else if (data.get(0).equals("Terminate")){
                this.writer.println(this.manager.terminate(data));
                break;
            }
        }


    }
}
