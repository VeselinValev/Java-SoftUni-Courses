import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CarManager manager = new CarManager();
        for (String input = reader.readLine(); !input.equals("Cops Are Here"); input = reader.readLine()){
            String[] tokens = input.split(" ");
            switch (tokens[0]){
                case "register":
                    manager.register(Integer.parseInt(tokens[1]), tokens[2], tokens[3], tokens[4],
                            Integer.parseInt(tokens[5]),Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]),
                            Integer.parseInt(tokens[8]), Integer.parseInt(tokens[9]));
                    break;
                case "check":
                    System.out.println(manager.check(Integer.parseInt(tokens[1])));
                    break;
                case "open":
                    if (tokens.length == 6){
                        manager.open(Integer.parseInt(tokens[1]), tokens[2], Integer.parseInt(tokens[3]),
                                tokens[4], Integer.parseInt(tokens[5]));
                    }else{
                        manager.open(Integer.parseInt(tokens[1]), tokens[2], Integer.parseInt(tokens[3]),
                                tokens[4], Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));
                    }
                    break;
                case "participate":
                    manager.participate(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                    break;
                case "start":
                    System.out.println(manager.start(Integer.parseInt(tokens[1])));
                    break;
                case "park":
                    manager.park(Integer.parseInt(tokens[1]));
                    break;
                case "unpark":
                    manager.unpark(Integer.parseInt(tokens[1]));
                    break;
                case "tune":
                    manager.tune(Integer.parseInt(tokens[1]), tokens[2]);
                    break;
                default:break;
            }
        }
    }
}
