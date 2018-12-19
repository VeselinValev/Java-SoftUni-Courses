package app.models.dtos.views;

import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserNamesAndProducts {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElement(name = "sold-products")
    private List<ProductNamePriceBuyerNames> soldProducts;

    public UserNamesAndProducts() {
    }

    public UserNamesAndProducts(String firstName, String lastName, List<ProductNamePriceBuyerNames> soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.soldProducts = soldProducts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ProductNamePriceBuyerNames> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductNamePriceBuyerNames> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
