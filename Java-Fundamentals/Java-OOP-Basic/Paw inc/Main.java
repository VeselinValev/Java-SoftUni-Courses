import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        AnimalCenterManager manager = new AnimalCenterManager();
        for (String input = reader.readLine(); !input.equals("Paw Paw Pawah"); input = reader.readLine()){
            String[] tokens = input.split(" \\| ");
            switch (tokens[0]){
                case "RegisterCleansingCenter":
                    manager.registerCleansingCenter(tokens[1]);
                    break;
                case "RegisterAdoptionCenter":
                    manager.registerAdoptionCenter(tokens[1]);
                    break;
                case "RegisterCastrationCenter":
                    manager.registerCastrationCenter(tokens[1]);
                    break;
                case "RegisterDog":
                    manager.registerDog(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), tokens[4]);
                    break;
                case "RegisterCat":
                    manager.registerCat(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), tokens[4]);
                    break;
                case "SendForCleansing":
                    manager.sendForCleansing(tokens[1], tokens[2]);
                    break;
                case "SendForCastration":
                    manager.sendForCastration(tokens[1], tokens[2]);
                    break;
                case "Cleanse":
                    manager.cleanse(tokens[1]);
                    break;
                case "Castrate":
                    manager.castrate(tokens[1]);
                    break;
                case "Adopt":
                    manager.adopt(tokens[1]);
                    break;
                case "CastrationStatistics":
                    System.out.println(manager.printCastrationStatistics());
                default:break;
            }
        }
        manager.printStatistics();
    }
}
