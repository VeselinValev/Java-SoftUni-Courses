package app.models.dtos.views.wrappers;

import app.models.dtos.views.ProductNamePrice;
import app.models.dtos.views.ProductNamePriceSellerName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductNamePriceSellerNameWrapper {

    @XmlElement(name = "product")
    private List<ProductNamePriceSellerName> products;

    public ProductNamePriceSellerNameWrapper() {
    }

    public List<ProductNamePriceSellerName> getProducts() {
        return products;
    }

    public void setProducts(List<ProductNamePriceSellerName> products) {
        this.products = products;
    }
}
