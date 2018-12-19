package app.models.dtos.views;

import java.util.List;

public class SoldProducts {
    private Integer count;

    private List<ProductNamePrice> products;

    public SoldProducts(List<ProductNamePrice> soldProducts) {
        this.products = soldProducts;
        this.count = soldProducts.size();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ProductNamePrice> getProducts() {
        return products;
    }

    public void setProducts(List<ProductNamePrice> soldProducts) {
        this.products = soldProducts;
    }
}
