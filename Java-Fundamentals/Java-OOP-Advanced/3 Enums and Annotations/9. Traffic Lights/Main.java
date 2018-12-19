import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> input = Arrays.stream(reader.readLine().split(" ")).collect(Collectors.toList());
        int n = Integer.parseInt(reader.readLine());
        LightStates[] lightStates = LightStates.values();
        for (int i = 0; i < n; i++){
            List<String> result = new ArrayList<>();
            for (String light: input){
                int index = Arrays.asList(lightStates).indexOf(LightStates.valueOf(light)) + 1;
                if (index >= lightStates.length){
                    index = 0;
                }
               result.add(lightStates[index].name());
            }
            System.out.println(String.join(" ",result));
            input = result;
        }
    }
}
