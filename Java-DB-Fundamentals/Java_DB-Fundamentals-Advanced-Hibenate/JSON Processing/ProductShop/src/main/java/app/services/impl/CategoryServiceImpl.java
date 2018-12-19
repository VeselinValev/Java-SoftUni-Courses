package app.services.impl;

import app.models.dtos.binding.CategoryDto;
import app.models.dtos.views.CategoryAllProducts;
import app.models.entities.Category;
import app.models.entities.Product;
import app.repositories.CategoryRepository;
import app.services.api.CategoryService;
import app.services.api.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;
    private ProductService productService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
        this.modelMapper = new ModelMapper();
    }

    @Override
    @Transactional
    public void saveAll(CategoryDto[] categories) {
        Random random = new Random();
        for (CategoryDto categoryDto : categories) {
            Category category = new Category();
            this.modelMapper.map(categoryDto, category);
            for (int i = 0; i < random.nextInt(15) + 1; i++) {
                Product product = this.productService.getRandomEntity();
                category.addProduct(product);
            }
            this.categoryRepository.save(category);
        }
    }

    @Override
    public List<CategoryAllProducts> getAllCategoriesAndTheirProducts() {
        return this.categoryRepository.getCategoriesByProductsCount();
    }
}
