package firstLastList;

import tests.FirstLastListFactory;
import tests.Product;

public class Main {

    public static void main(String[] args) {
        IFirstLastList<Product> items = FirstLastListFactory.create();
        items.add(new Product(0.50, "coffee"));
        items.add(new Product(1.20, "mint drops"));
        items.add(new Product(1.20, "beer"));
        items.add(new Product(2.22, "chocolate"));
        items.add(new Product(0.50, "candy"));
        items.add(new Product(0.01, "cent"));
        items.add(new Product(1.20, "cola"));

        // Act
        int countRemoved = items.removeAll(new Product(1.20, null));
        System.out.println();
    }
}
