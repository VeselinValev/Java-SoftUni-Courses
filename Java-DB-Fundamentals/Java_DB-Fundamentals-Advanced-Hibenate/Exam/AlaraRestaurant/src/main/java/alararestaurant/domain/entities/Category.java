package alararestaurant.domain.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "categories")
public class Category extends BaseEntity {

    @Column(name = "name", nullable = false)
    @Length(min = 3, max = 30)
    private String name;

    @OneToMany(mappedBy = "category", targetEntity = Item.class)
    private List<Item> items;

    public Category() {
        this.items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
