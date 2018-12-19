import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class CalculateSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(reader.readLine());
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(num);
        StringBuilder sb = new StringBuilder().append(num).append(", ");
        for (int i = 1; i < 17; i++){
            deque.addLast(deque.peekFirst() + 1);
            sb.append(deque.peekFirst() + 1).append(", ");
            deque.addLast(2*deque.peekFirst() + 1);
            sb.append(2*deque.peekFirst() + 1).append(", ");
            sb.append(deque.peekFirst() + 2).append(", ");
            deque.addLast(deque.pollFirst() + 2);
        }
        sb.append(deque.peekFirst() + 1);
        System.out.println(sb);
    }
}