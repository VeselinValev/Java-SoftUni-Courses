package app.models.dtos.views;

import java.util.List;

public class UserNamesAndProducts {
    private String firstName;

    private String lastName;

    private List<ProductNamePriceBuyerNames> soldProducts;

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
