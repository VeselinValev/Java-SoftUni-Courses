import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new StackImpl();
        for (String input = reader.readLine(); !input.equals("END"); input = reader.readLine()){
             String[] commands = input.split(" ");
             if (commands[0].equals("Push")){
                 StringBuilder sb = new StringBuilder();
                 for (int i = 1; i < commands.length; i++){
                    sb.append(commands[i]);
                 }
                 int[] numbers = Arrays.stream(sb.toString().split(",")).mapToInt(Integer::parseInt).toArray();
                 for (int i = 0; i < numbers.length; i++){
                     stack.push(numbers[i]);
                 }
             }else if (commands[0].equals("Pop")){
                 try{
                     stack.pop();
                 }catch (NoSuchElementException nse){
                     System.out.println(nse.getMessage());
                 }
             }
        }
        for (int i = 0; i < 2; i++){
            for (Integer number: stack){
                System.out.println(number);
            }
        }
    }
}