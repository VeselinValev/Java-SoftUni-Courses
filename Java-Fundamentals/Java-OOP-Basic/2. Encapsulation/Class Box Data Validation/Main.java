package exerciseOOP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double length = Double.parseDouble(reader.readLine());
        double width = Double.parseDouble(reader.readLine());
        double height = Double.parseDouble(reader.readLine());

        try{
            Box box = new Box(length, width, height);
            System.out.printf("Surface Area - %.2f\n", box.surfaceArea());
            System.out.printf("Lateral Surface Area - %.2f\n", box.lateralSurfaceArea());
            System.out.printf("Volume - %.2f\n", box.volume());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }


    }
}
