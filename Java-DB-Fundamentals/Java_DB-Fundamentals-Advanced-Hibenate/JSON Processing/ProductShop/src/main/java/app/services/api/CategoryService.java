package app.services.api;

import app.models.dtos.binding.CategoryDto;
import app.models.dtos.views.CategoryAllProducts;

import java.util.List;

public interface CategoryService {
    void saveAll(CategoryDto[] categories);
    List<CategoryAllProducts> getAllCategoriesAndTheirProducts();
}
