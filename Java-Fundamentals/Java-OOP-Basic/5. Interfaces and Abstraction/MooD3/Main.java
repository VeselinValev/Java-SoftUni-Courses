package MooD3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" \\| ");
        if (input[1].equals("Demon")){
            Demon demon = new DemonImpl(input[0], input[1], Integer.parseInt(input[3]), Double.parseDouble(input[2]));
            System.out.println(demon.toString());
        }else{
            Archangel archangel = new ArchangelImpl(input[0], input[1], Integer.parseInt(input[3]), Integer.parseInt(input[2]));
            System.out.println(archangel.toString());
        }
    }
}
