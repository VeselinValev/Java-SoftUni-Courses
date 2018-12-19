import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfNodes = Integer.parseInt(reader.readLine());
        Map<Integer, Tree> allTrees = new HashMap<>();
        int rootValue = 0;
        for (int i = 0; i < numberOfNodes - 1; i++){
            int[] nodeInfo = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (i == 0){
                rootValue = nodeInfo[0];
            }
            allTrees.putIfAbsent(nodeInfo[0], new Tree(nodeInfo[0]));
            allTrees.putIfAbsent(nodeInfo[1], new Tree(nodeInfo[1]));
            allTrees.get(nodeInfo[0]).addChild(allTrees.get(nodeInfo[1]).getRoot());
        }
        Tree mainTree = allTrees.get(rootValue);
        int givenSum = Integer.parseInt(reader.readLine());
        List<Tree> resultTrees = new ArrayList<>();

        System.out.printf("Subtrees of sum %s:%n", givenSum);
        DFSCheckSubtrees(mainTree, givenSum, resultTrees);
        for (Tree root : resultTrees) {
            printChildren(root);
            System.out.println();
        }
    }

    private static void printChildren(Tree root) {
        System.out.print(root.getRoot().getValue() + " ");
        for (int i = 0; i < root.getRoot().getChildren().size(); i++){
            Tree childTree = new Tree();
            childTree.setRoot(root.getRoot().getChildren().get(i));
            printChildren(childTree);
        }
    }

    private static int DFSCheckSubtrees(Tree mainTree, int givenSum, List<Tree> resultTree){
        int current = mainTree.getRoot().getValue();
        for (int i = 0; i < mainTree.getRoot().getChildren().size(); i++){
            Tree childTree = new Tree();
            childTree.setRoot(mainTree.getRoot().getChildren().get(i));
            current += DFSCheckSubtrees(childTree, givenSum, resultTree);
        }
        if (current == givenSum) {
            resultTree.add(mainTree);
        }
        return current;
    }
}
