import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        List<Box> boxes = new ArrayList<>();
        for (int i = 0; i < n; i++){
            boxes.add(new Box(reader.readLine()));
        }
        String toCompare = reader.readLine();
        int counter = 0;
        for (Box box: boxes){
            if (box.compareTo(toCompare) > 0){
                counter++;
            }
        }
        System.out.println(counter);
    }
}