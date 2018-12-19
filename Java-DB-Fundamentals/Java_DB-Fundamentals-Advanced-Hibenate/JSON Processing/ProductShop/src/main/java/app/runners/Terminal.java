package app.runners;

import app.constants.InputFilePaths;
import app.constants.OutputFilePaths;
import app.io.JSONparser;
import app.models.dtos.binding.CategoryDto;
import app.models.dtos.binding.ProductDto;
import app.models.dtos.binding.UserDto;
import app.models.dtos.views.*;
import app.services.api.CategoryService;
import app.services.api.ProductService;
import app.services.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Component
public class Terminal implements CommandLineRunner {

    private UserService userService;
    private ProductService productService;
    private CategoryService categoryService;
    private JSONparser parser;
    private ModelMapper modelMapper;

    @Autowired
    public Terminal(UserService userService, ProductService productService, CategoryService categoryService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.parser = new JSONparser();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void run(String... args) throws IOException {
        seedDatabase();

        //Problem 1
        productsInRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        //Problem 2
        successfullySoldProducts();

        //Problem 3
        categoriesByProductsCount();

        //Problem4
        usersAndProducts();
    }

    private void seedDatabase() throws FileNotFoundException {
        UserDto[] userDtos = this.parser.fromJSONtoObject(UserDto[].class, InputFilePaths.USERS_FILE_PATH);
        this.userService.saveAll(userDtos);

        ProductDto[] productDtos = this.parser.fromJSONtoObject(ProductDto[].class, InputFilePaths.PRODUCTS_FILE_PATH);
        this.productService.saveAll(productDtos);

        CategoryDto[] categoryDtos = this.parser.fromJSONtoObject(CategoryDto[].class, InputFilePaths.CATEGORIES_FILE_PATH);
        this.categoryService.saveAll(categoryDtos);
    }

    private void productsInRange(BigDecimal priceFrom, BigDecimal priceTo) throws IOException {
        List<ProductNamePriceSellerName> dtoProducts = this.productService.getAllProductsAndConvertToDto(priceFrom, priceTo);
        this.parser.fromObjectToJSON(dtoProducts, OutputFilePaths.PRODUCTS_IN_RANGE);
    };

    private void successfullySoldProducts() throws IOException {
        List<UserNamesAndProducts> dtoUsers = this.userService.getUserWhoSoldProducts();
        this.parser.fromObjectToJSON(dtoUsers, OutputFilePaths.USERS_SOLD_PRODUCTS);
    }

    private void  categoriesByProductsCount() throws IOException {
        List<CategoryAllProducts> dtoCategories = this.categoryService.getAllCategoriesAndTheirProducts();
        this.parser.fromObjectToJSON(dtoCategories, OutputFilePaths.CATEGORIES_BY_PRODUCT);
    }

    private void usersAndProducts() throws IOException {
        List<UserNamesWithAgeAndProducts> dtoUsers = this.userService.getUserWhoSoldProductsWithAge();
        List<UsersProducts> usersProducts = new ArrayList<>();
        usersProducts.add(new UsersProducts(dtoUsers));
        this.parser.fromObjectToJSON(usersProducts, OutputFilePaths.USERS_AND_PRODUCTS);
    }
}
