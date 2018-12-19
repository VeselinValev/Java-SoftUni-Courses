import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> theNumbers = Arrays.stream(reader.readLine()
                .split("\\s+")).map(Integer::parseInt)
                .collect(Collectors.toList());
        ArrayDeque<Integer> inputNumbers = Arrays.stream(reader.readLine()
                .split("\\s+")).map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));
        List<Integer> numbersToPrint = new ArrayList<>();
        int index = 0;
        while(true){
            if(numbersToPrint.size() == 6){
                break;
            }
            int numberFromQueue = inputNumbers.pop();
            if (numberFromQueue % theNumbers.get(index) == 0){
                numbersToPrint.add(numberFromQueue);
                theNumbers.remove(index);
            }else{
                numberFromQueue++;
                inputNumbers.addLast(numberFromQueue);
            }
        }
        System.out.println(numbersToPrint.toString().replaceAll("[\\[\\]]", ""));
    }
}
