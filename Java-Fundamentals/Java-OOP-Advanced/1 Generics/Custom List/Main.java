import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CustomList<String> list = new CustomList<>();
        for (String input = reader.readLine(); !input.equals("END"); input = reader.readLine()){
             String[] commands = input.split(" ");
             switch (commands[0]){
                 case "Add": list.add(commands[1]);break;
                 case "Remove": list.remove(Integer.parseInt(commands[1]));break;
                 case "Contains": System.out.println(list.contains(commands[1]));;break;
                 case "Swap": list.swap(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]) );break;
                 case "Greater": System.out.println(list.countGreaterThan(commands[1]));break;
                 case "Max": System.out.println(list.getMax());break;
                 case "Min": System.out.println(list.getMin());break;
                 case "Print": System.out.println(list.toString());break;
                 default:break;
             }
        }
    }
}