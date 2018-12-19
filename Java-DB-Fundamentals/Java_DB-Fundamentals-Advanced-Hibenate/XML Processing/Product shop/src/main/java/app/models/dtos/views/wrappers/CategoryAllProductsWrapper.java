package app.models.dtos.views.wrappers;

import app.models.dtos.views.CategoryAllProducts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryAllProductsWrapper {

    @XmlElement(name = "category")
    private List<CategoryAllProducts> categories;

    public CategoryAllProductsWrapper() {
    }

    public List<CategoryAllProducts> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryAllProducts> categories) {
        this.categories = categories;
    }
}
