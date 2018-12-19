import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ListyIterator listyIterator = null;
        for (String input = reader.readLine(); !input.equals("END"); input = reader.readLine()){
            String[] commands = input.split(" ");
             switch (commands[0]){
                 case "Create":
                     if (commands.length > 1){
                         listyIterator = new ListyIterator(Arrays.stream(commands).skip(1).collect(Collectors.toList()));
                     }else{
                         listyIterator = new ListyIterator(new ArrayList<>());
                     }
                     break;
                 case "Move":
                     System.out.println(listyIterator.move());
                     break;
                 case "HasNext":
                     System.out.println(listyIterator.hasNext());
                     break;
                 case "Print":
                     try{
                         listyIterator.print();
                     }catch (NoSuchElementException nse){
                         System.out.println(nse.getMessage());
                     }
             }
        }
    }
}