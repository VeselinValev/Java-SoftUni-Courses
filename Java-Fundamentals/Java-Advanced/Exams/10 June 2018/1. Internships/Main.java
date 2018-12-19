import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());
        ArrayDeque<String> allCandidates = new ArrayDeque();
        ArrayDeque<String> allProblems = new ArrayDeque();
        for (int i = 0; i < n; i++) {
            String inputProblem = reader.readLine();
            allProblems.addLast(inputProblem);
        }
        for (int i = 0; i < m; i++) {
            String inputCandidate = reader.readLine();
            Pattern pattern = Pattern.compile("^([A-Z][a-z]+) ([A-Z][a-z]+)$");
            Matcher matcher = pattern.matcher(inputCandidate);
            if (matcher.matches()) {
                allCandidates.addLast(inputCandidate);
            }
        }
        while (!allProblems.isEmpty() && allCandidates.size() > 1) {
            String currentCandidate = allCandidates.pollFirst();
            String currentProblem = allProblems.pollLast();
            if (solvingProblem(currentCandidate, currentProblem)) {
                allCandidates.addLast(currentCandidate);
                System.out.printf("%s solved %s.%n", currentCandidate, currentProblem);
            } else {
                allProblems.addFirst(currentProblem);
                System.out.printf("%s failed %s.%n", currentCandidate, currentProblem);
            }
        }
        if (allCandidates.size() == 1) {
            System.out.printf("%s gets the job!%n", allCandidates.pollLast());
        } else {
            System.out.println(allCandidates.toString().replaceAll("[\\[\\]]", ""));
        }
    }

    public static boolean solvingProblem(String candidate, String problem) {
        return candidate.codePoints().sum() > problem.codePoints().sum();
    }
}

