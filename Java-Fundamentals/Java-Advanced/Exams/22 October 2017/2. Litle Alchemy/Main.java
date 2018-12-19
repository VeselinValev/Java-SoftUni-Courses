import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> queue = Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));
        int goldStonesCount = 0;
        for (String input = reader.readLine(); !input.equals("Revision"); input = reader.readLine()) {
            String[] tokens = input.split(" ");
            int n = Integer.parseInt(tokens[2]);
            if (tokens[0].equals("Apply")) {
                while (!queue.isEmpty() && n-- > 0) {
                    int current = queue.poll();
                    if (--current == 0) {
                        goldStonesCount++;
                    } else {
                        queue.offer(current);
                    }
                }

            } else {
                if (goldStonesCount > 0) {
                    queue.offer(n);
                    goldStonesCount--;
                }
            }
        }
        System.out.println(queue.stream().map(Object::toString).collect(Collectors.joining(" ")));
        System.out.println(goldStonesCount);
    }
}