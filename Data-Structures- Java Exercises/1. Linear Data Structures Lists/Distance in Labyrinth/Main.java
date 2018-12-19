import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static List<List<String>> labyrinth;
    public static ArrayDeque<int[]> queueOfCells;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        labyrinth = new ArrayList<>();
        queueOfCells = new ArrayDeque<>();
        queueOfCells.addLast(fillMatrixAndFindStartCell(n, reader));
        traverseLabyrinth();
        labyrinth.forEach(x -> System.out.println(x.stream().map(y -> y = y.equals("0")? "u": y)
                .collect(Collectors.toList()).toString().replaceAll("[\\[\\],\\s]", "")));
    }

    public static void findPath(int counter, int row, int col) {
        if (!(row < 0 || row >= labyrinth.size() || col < 0 || col >= labyrinth.size())) {
            if (labyrinth.get(row).get(col).equals("0")) {
                labyrinth.get(row).set(col, String.format("%s", counter));
                queueOfCells.addLast(new int[]{counter + 1, row, col});
            }
        }
    }

    public static int[] fillMatrixAndFindStartCell(int n, BufferedReader reader) throws IOException {
        int[] startCell = {1, 0, 0};
        for (int i = 0; i < n; i++) {
            labyrinth.add(Arrays.stream(reader.readLine().split("")).collect(Collectors.toList()));
            int index = labyrinth.get(i).indexOf("*");
            if (index > -1) {
                startCell[1] = i;
                startCell[2] = index;
            }
        }
        return startCell;
    }

    public static void traverseLabyrinth() {
        while (!queueOfCells.isEmpty()) {
            int[] currentCell = queueOfCells.pollFirst();
            findPath(currentCell[0], currentCell[1] + 1, currentCell[2]);
            findPath(currentCell[0], currentCell[1] - 1, currentCell[2]);
            findPath(currentCell[0], currentCell[1], currentCell[2] + 1);
            findPath(currentCell[0], currentCell[1], currentCell[2] - 1);
        }
    }
}
