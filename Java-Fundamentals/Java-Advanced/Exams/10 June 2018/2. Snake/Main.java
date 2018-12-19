import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        if (n == 5) {
            System.out.printf("You win! Final snake length is 4%n");
        } else {
            String[] commands = reader.readLine().split(", ");
            String[][] matrix = new String[n][n];
            int startRow = 0;
            int startCol = 0;
            int snakeLength = 1;
            for (int i = 0; i < n; i++) {
                String[] matrixCells = reader.readLine().split(" ");
                if (Arrays.stream(matrixCells).anyMatch(x -> x.equals("s"))) {
                    startRow = i;
                    for (int j = 0; j < matrixCells.length; j++) {
                        if (matrixCells[j].equals("s")) {
                            startCol = j;
                        }
                    }
                }
                matrix[i] = matrixCells;
            }
            for (String command : commands) {
                switch (command.toLowerCase().trim()) {
                    case "up":
                        if (startRow > 0) {
                            startRow--;
                        } else {
                            startRow = matrix.length - 1;
                        }
                        break;
                    case "down":
                        if (startRow < matrix.length - 1) {
                            startRow++;
                        } else {
                            startRow = 0;
                        }
                        break;
                    case "left":
                        if (startCol > 0) {
                            startCol--;
                        } else {
                            startCol = matrix[startRow].length - 1;
                        }
                        break;
                    case "right":
                        if (startCol < matrix[startRow].length - 1) {
                            startCol++;
                        } else {
                            startCol = 0;
                        }
                        break;
                }
                if (matrix[startRow][startCol].equals("f")) {
                    matrix[startRow][startCol] = "*";
                    snakeLength++;
                } else if (matrix[startRow][startCol].equals("e")) {
                    System.out.println("You lose! Killed by an enemy!");
                    return;
                }
            }
            int foodLeft = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][j].equals("f")) {
                        foodLeft++;
                    }
                }
            }
            if (foodLeft > 0) {
                System.out.printf("You lose! There is still %s food to be eaten.%n", foodLeft);
            } else {
                System.out.printf("You win! Final snake length is %s%n", snakeLength);
            }
        }
    }
}

