package app.models.dtos.views;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProducts {

    @XmlAttribute
    private Integer count;

    @XmlElement(name = "product")
    private List<ProductNamePrice> products;

    public SoldProducts() {
    }

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
