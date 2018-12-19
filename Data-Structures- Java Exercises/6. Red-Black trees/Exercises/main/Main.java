import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        ArrayDeque<Integer> numbers = new ArrayDeque<>();
        if (n == m) {
            System.out.println(n);
        } else if  (n < m){
            numbers.addFirst(m);
            while (m != n) {
                if (m < 6){
                    while(m != n){
                        if (m - 2 >= n){
                            m = m- 2;
                        }else if (m - 1 >= n){
                            m = m - 1;
                        }
                        numbers.addLast(m);
                    }
                    break;
                }
                if (m / 2 >= n) {
                    if (m % 2 != 0) {
                        m = m - 1;
                        numbers.addLast(m);
                    }
                    m = m / 2;
                } else if (m - 2 >= n) {
                    m = m - 2;
                } else if (m - 1 >= n) {
                    m = m - 1;
                }
                numbers.addLast(m);
            }
            StringBuilder sb = new StringBuilder();
            while (!numbers.isEmpty()) {
                sb.append(numbers.pollLast()).append(" -> ");
            }
            System.out.println(sb.substring(0, sb.length() - 4));
        }
    }
}
