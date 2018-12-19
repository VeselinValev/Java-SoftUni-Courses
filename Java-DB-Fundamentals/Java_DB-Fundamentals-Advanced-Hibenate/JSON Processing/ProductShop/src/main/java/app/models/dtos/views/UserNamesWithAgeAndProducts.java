package app.models.dtos.views;

public class UserNamesWithAgeAndProducts {
    private String firstName;

    private String lastName;

    private Integer age;

    private SoldProducts soldProducts;

    public UserNamesWithAgeAndProducts(String firstName, String lastName, Integer age, SoldProducts soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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

    public SoldProducts getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(SoldProducts soldProducts) {
        this.soldProducts = soldProducts;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
