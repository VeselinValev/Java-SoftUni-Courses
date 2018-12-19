import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        List<Box> boxes = new ArrayList<>();
        for (int i = 0; i < n; i++){
            boxes.add(new Box(Integer.parseInt(reader.readLine())));
        }
        int[] swapCommand = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        Box temp = new Box(boxes.get(swapCommand[1]).getItem());
        boxes.set(swapCommand[1], boxes.get(swapCommand[0]));
        boxes.set(swapCommand[0], temp);
        boxes.forEach(x -> System.out.println(x.toString()));
    }
}