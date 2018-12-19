import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static Map<Integer, Tree<Integer>> nodeValues = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        readTree();
        List<Integer> leaves = findMiddleNodes();
        System.out.println("Middle nodes: " + leaves.toString().replaceAll("[\\[\\],]", ""));
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
    public static List<Integer> findMiddleNodes(){
        List<Integer> middleNodes = new ArrayList<>();
        for (Tree<Integer> tree: nodeValues.values()){
            if (!tree.children.isEmpty() && tree.parent != null){
                middleNodes.add(tree.value);
            }
        }
        middleNodes.sort(Integer::compare);
        return middleNodes;
    }
}
