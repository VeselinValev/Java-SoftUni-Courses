package callofduty.engine;

import callofduty.interfaces.InputReader;
import callofduty.interfaces.MissionManager;
import callofduty.interfaces.OutputWriter;
import callofduty.interfaces.Runnable;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Engine implements Runnable {
    private InputReader reader;
    private OutputWriter writer;
    private MissionManager missionManager;

    public Engine(InputReader reader, OutputWriter writer, MissionManager missionManager) {
        this.reader = reader;
        this.writer = writer;
        this.missionManager = missionManager;
    }

    @Override
    public void run() throws IOException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        while(true){
            List<String> data = Arrays.stream(reader.readLine().split(" ")).collect(Collectors.toList());
            if ("Agent".equals(data.get(0))){
                writer.println(missionManager.agent(data));
            } else if ("Request".equals(data.get(0))){
                writer.println(missionManager.request(data));
            }else if ("Status".equals(data.get(0))){
                writer.println(missionManager.status(data));
            }else if ("Complete".equals(data.get(0))){
                writer.println(missionManager.complete(data));
            }else if ("Over".equals(data.get(0))){
                writer.println(missionManager.over(data));
                break;
            }
        }
    }
}
