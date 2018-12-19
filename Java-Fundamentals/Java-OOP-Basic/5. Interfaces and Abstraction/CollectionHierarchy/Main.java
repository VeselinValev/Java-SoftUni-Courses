package CollectionHierarchy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int removes = Integer.parseInt(reader.readLine());
        AddCollection addCollection = new AddCollectionImpl();
        AddRemoveCollection addRemoveCollection = new AddRemoveCollectionImpl();
        MyList myList = new MyListImpl();
        for (String word: input){
            addCollection.addIndex(addCollection.addToCollection(word));
            addRemoveCollection.addIndex(addRemoveCollection.addToCollection(word));
            myList.addIndex(myList.addToCollection(word));
        }
        for (int i = 0; i < removes; i++){
            addRemoveCollection.addRemoved(addRemoveCollection.removeFromCollection());
            myList.addRemoved(myList.removeFromCollection());
        }
        System.out.println(addCollection.getIndices().toString().replaceAll("[\\[\\],]", ""));
        System.out.println(addRemoveCollection.getIndices().toString().replaceAll("[\\[\\],]", ""));
        System.out.println(myList.getIndices().toString().replaceAll("[\\[\\],]", ""));
        System.out.println(addRemoveCollection.getRemoved().toString().replaceAll("[\\[\\],]", ""));
        System.out.println(myList.getRemoved().toString().replaceAll("[\\[\\],]", ""));
    }
}
