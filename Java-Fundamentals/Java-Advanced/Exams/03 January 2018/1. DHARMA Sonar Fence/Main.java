import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int numberOfApplesEaten = 0;
    public static int numberOfApples = 0;
    public static List<List<String>> matrix = new ArrayList<>();
    public static boolean endOfProgram = false;
    public static int[] snakePosition = new int[3];
    public static boolean isSnakeDead = false;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        createMatrix(reader, n);
        findSnakePosition();
        processDirection(reader);
        print(isSnakeDead);
    }

    public static void processDirection(BufferedReader reader) throws IOException {
        String prevDirection = reader.readLine();
        while (true) {
            String[] input = reader.readLine().split(" ");
            boolean toBreak = false;
            if (input[0].equals("end")) {
                toBreak = true;
            }
            String direction = input[0];
            int steps = Integer.parseInt(input[2]);
            moveSnake(prevDirection, steps);

            if (toBreak) {
                break;
            }
            prevDirection = direction;
        }
    }

    public static void findSnakePosition() {
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                for (int k = 0; k < matrix.size(); k++) {
                    if (matrix.get(i).get(j).charAt(k) == 's') {
                        snakePosition[0] = i;
                        snakePosition[1] = j;
                        snakePosition[2] = k;
                    }
                }
            }
        }
    }

    public static void createMatrix(BufferedReader reader, int n) throws IOException {
        for (int i = 0; i < n; i++) {
            matrix.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            String input = reader.readLine();
            String[] tokens = input.split(" \\| ");
            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(j) == 'a') {
                    numberOfApples++;
                }
            }
            for (int j = 0; j < matrix.size(); j++) {
                matrix.get(j).add(tokens[j]);
            }
        }
    }

    public static void print(boolean isSnakeDead) {
        System.out.printf("Points collected: %s%n", numberOfApplesEaten);
        if (isSnakeDead) {
            System.out.println("The snake dies.");
        }
    }

    public static void moveSnake(String direction, int steps) {
        switch (direction) {
            case "up":

                break;
            case "down":
                for (int i = 0; i < steps; i++) {
                    checkIfApple(snakePosition[0] + 1, snakePosition[1], snakePosition[2]);
                    if (!endOfProgram) {
                        snakePosition[0]++;
                    }
                }
                break;
            case "left":
                for (int i = 0; i < steps; i++) {
                    checkIfApple(snakePosition[0], snakePosition[1], snakePosition[2] - 1);
                    if (!endOfProgram) {
                        snakePosition[2]--;
                    }
                }
                break;
            case "right":
                for (int i = 0; i < steps; i++) {
                    checkIfApple(snakePosition[0], snakePosition[1], snakePosition[2] + 1);
                    if (!endOfProgram) {
                        snakePosition[2]++;
                    }
                }
                break;
            case "forward":
                for (int i = 0; i < steps; i++) {
                    checkIfApple(snakePosition[0], snakePosition[1] - 1, snakePosition[2]);
                    if (!endOfProgram) {
                        snakePosition[1]--;
                    }
                }
                break;
            case "backward":
                for (int i = 0; i < steps; i++) {
                    checkIfApple(snakePosition[0], snakePosition[1] + 1, snakePosition[2]);
                    if (!endOfProgram) {
                        snakePosition[1]++;
                    }
                }
                break;
            default:
                break;
        }
    }
    public static void moveInDirection(int x, int y, int z, int index){
        for (int i = 0; i < steps; i++) {
            checkIfApple(snakePosition[0] - 1, snakePosition[1], snakePosition[2]);
            if (!endOfProgram) {
                snakePosition[0]--;
            }
        }
    }

    public static void checkIfApple(int x, int y, int z) {
        try {
            if (matrix.get(x).get(y).charAt(z) == 'a') {
                numberOfApplesEaten++;
                matrix.get(x).set(y, matrix.get(x).get(y).substring(0, z) + "o" + matrix.get(x).get(y).substring(z + 1, matrix.get(x).get(y).length()));
            }
        } catch (IndexOutOfBoundsException iobe) {
            isSnakeDead = true;
            endOfProgram = true;
        }
    }
}
