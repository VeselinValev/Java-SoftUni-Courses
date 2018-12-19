package app.models.dtos.binding;

import java.io.Serializable;

public class CategoryDto implements Serializable {

    private String name;

    public CategoryDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}