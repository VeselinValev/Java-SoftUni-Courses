import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static Map<Integer, Tree<Integer>> nodeValues = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        readTree(reader);
        int sum = Integer.parseInt(reader.readLine());
        List<Tree<Integer>> leaves = findLeaves();
        List<ArrayDeque<Integer>> paths = getPaths(leaves, sum);
        System.out.printf("Paths of sum %s:%n", sum);
        for (ArrayDeque<Integer> path: paths){
            while (!path.isEmpty()){
                System.out.print(path.pollLast() + " ");
            }
            System.out.println();
        }
    }
    public static Tree<Integer> findNode(int value){
        if (!nodeValues.containsKey(value)){
            nodeValues.put(value, new Tree<>(value));
        }
        return nodeValues.get(value);
    }
    public static void addEdge(int parent, int child){
        Tree<Integer> parentNode;
        Tree<Integer> childNode;
        if (!nodeValues.containsKey(parent)){
            parentNode = new Tree<>(parent);
        }else{
            parentNode = nodeValues.get(parent);
        }
        if (!nodeValues.containsKey(child)){
            childNode = new Tree<>(child);
        }else{
            childNode = nodeValues.get(child);
        }
        nodeValues.putIfAbsent(parent, parentNode);
        nodeValues.putIfAbsent(child, childNode);
        parentNode.children.add(childNode);
        childNode.parent = parentNode;
    }
    public static void readTree(BufferedReader reader) throws IOException {
        int nodeCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < nodeCount - 1; i++){
            String[] input = reader.readLine().split(" ");
            addEdge(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }
    }
    public static List<Tree<Integer>> findLeaves(){
        List<Tree<Integer>> leaves = new ArrayList<>();
        for (Tree<Integer> tree: nodeValues.values()){
            if (tree.children.isEmpty()){
                leaves.add(tree);
            }
        }
        return leaves;
    }
    public static List<ArrayDeque<Integer>> getPaths(List<Tree<Integer>> leaves, int sum){
        List<ArrayDeque<Integer>> paths = new ArrayList<>();
        for (Tree<Integer> leaf: leaves){
            int currentSum = 0;
            ArrayDeque<Integer> currentPath = new ArrayDeque<>();
            while (leaf != null){
                currentSum += leaf.value;
                currentPath.addLast(leaf.value);
                leaf = leaf.parent;
            }
            if (sum == currentSum){
                paths.add(currentPath);
            }
        }
        return paths;
    }
}
