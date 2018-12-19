import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static Map<Integer, Tree<Integer>> nodeValues = new LinkedHashMap<>();
    public static int counter = 0;
    public static Pair<Integer, Integer> deepestNode = new Pair<>(0,0);
    public static void main(String[] args) throws IOException {
        readTree();
        findDeepestNodes(findRoot());
        System.out.println("Deepest node: " + deepestNode.getValue());
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
    public static void readTree() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodeCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < nodeCount - 1; i++){
            String[] input = reader.readLine().split(" ");
            addEdge(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }
    }
    public static void findDeepestNodes(Tree<Integer> parent){
        counter++;
        for (Tree<Integer> tree: parent.children){
            if (tree.children.isEmpty()){
                if (deepestNode.getKey() < counter){
                    deepestNode = new Pair<>(counter, tree.value);
                }
            }else{
                findDeepestNodes(tree);
            }
        }
        counter--;
    }
    public static Tree<Integer> findRoot(){
        return nodeValues.entrySet().stream().filter(x -> x.getValue().parent == null).findFirst().orElse(null).getValue();
    }
}
