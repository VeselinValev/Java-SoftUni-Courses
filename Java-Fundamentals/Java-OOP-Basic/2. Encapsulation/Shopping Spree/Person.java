package exerciseOOP;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<Product> productList;

    public String getName() {
        return name;
    }

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.productList = new ArrayList<Product>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    private void setMoney(double money) {
        if (money < 0){
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }
    public boolean hasEnoughMoney(double cost){
        return this.money >= cost;
    }
    public void buyProduct(Product product){
        this.productList.add(product);
        this.money -= product.getCost();
    }
    public List<Product> getProductList() {
        return productList;
    }

}
