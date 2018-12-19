import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays.stream(reader.readLine().split(",\\s+")).mapToInt(Integer::parseInt).toArray();
        char[][] matrix = new char[numbers.length][16];
        for (int i = 0; i < numbers.length; i++) {
            String temp = Integer.toBinaryString(numbers[i]);
            StringBuilder builder = new StringBuilder(temp);
            if (temp.length() < 16) {
                for (int j = 0; j < 16 - temp.length(); j++) {
                    builder.insert(0, '0');
                }
            }
            matrix[i] = builder.toString().toCharArray();
        }
        matrix = arangeMatrix(matrix);
        numbers = changeBinaryToDecimal(matrix);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numbers.length - 1; i++) {
            builder.append(numbers[i]).append(", ");
        }
        builder.append(numbers[numbers.length - 1]);
        System.out.println(builder);
    }

    public static char[][] arangeMatrix(char[][] matrix) {
        for (int i = 0; i < 16; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < matrix.length; j++) {
                builder.append(matrix[j][i]);
            }
            char[] toSort = builder.toString().toCharArray();
            Arrays.sort(toSort);

            for (int j = 0; j < matrix.length; j++) {
                matrix[j][i] = toSort[j];
            }
        }
        return matrix;
    }

    public static int[] changeBinaryToDecimal(char[][] matrix) {
        int[] numbers = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            String binary = String.valueOf(matrix[i]);
            numbers[i] = Integer.parseInt(binary, 2);
        }
        return numbers;
    }
}
