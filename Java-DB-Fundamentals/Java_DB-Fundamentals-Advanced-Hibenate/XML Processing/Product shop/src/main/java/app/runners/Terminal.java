package app.runners;

import app.constants.InputFilePaths;
import app.constants.OutputFilePaths;
import app.io.XMLparser;
import app.models.dtos.binding.*;
import app.models.dtos.views.*;
import app.models.dtos.views.wrappers.CategoryAllProductsWrapper;
import app.models.dtos.views.wrappers.ProductNamePriceSellerNameWrapper;
import app.models.dtos.views.wrappers.UserNamesAndProductsWrapper;
import app.models.dtos.views.wrappers.UsersProductsWrapper;
import app.services.api.CategoryService;
import app.services.api.ProductService;
import app.services.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
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
    private XMLparser parser;
    private ModelMapper modelMapper;

    @Autowired
    public Terminal(UserService userService, ProductService productService, CategoryService categoryService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.parser = new XMLparser();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void run(String... args) throws IOException, JAXBException {
        //seedDatabase();

        //Problem 1
        //productsInRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        //Problem 2
        //successfullySoldProducts();

        //Problem 3
        //categoriesByProductsCount();

        //Problem4
        usersAndProducts();
    }

    private void seedDatabase() throws JAXBException, FileNotFoundException {
        UserWrapperDto users = this.parser.fromXMLToObject(UserWrapperDto.class, InputFilePaths.USERS_FILE_PATH);
        this.userService.saveAll(users.getUsers());

        ProductWrapperDto products = this.parser.fromXMLToObject(ProductWrapperDto.class, InputFilePaths.PRODUCTS_FILE_PATH);
        this.productService.saveAll(products.getProducts());

        CategoryWrapperDto categories = this.parser.fromXMLToObject(CategoryWrapperDto.class, InputFilePaths.CATEGORIES_FILE_PATH);
        this.categoryService.saveAll(categories.getCategories());
    }

    private void productsInRange(BigDecimal priceFrom, BigDecimal priceTo) throws IOException, JAXBException {
        List<ProductNamePriceSellerName> products = this.productService.getAllProductsAndConvertToDto(priceFrom, priceTo);
        ProductNamePriceSellerNameWrapper productWrapper = new ProductNamePriceSellerNameWrapper();
        productWrapper.setProducts(products);
        this.parser.fromObjectToXML(ProductNamePriceSellerNameWrapper.class, productWrapper, OutputFilePaths.PRODUCTS_IN_RANGE);
    };

    private void successfullySoldProducts() throws IOException, JAXBException {
        List<UserNamesAndProducts> users = this.userService.getUserWhoSoldProducts();
        UserNamesAndProductsWrapper userWrapper = new UserNamesAndProductsWrapper();
        userWrapper.setUsers(users);
        this.parser.fromObjectToXML(UserNamesAndProductsWrapper.class, userWrapper, OutputFilePaths.USERS_SOLD_PRODUCTS);
    }

    private void  categoriesByProductsCount() throws IOException, JAXBException {
        List<CategoryAllProducts> categories = this.categoryService.getAllCategoriesAndTheirProducts();
        CategoryAllProductsWrapper categoryWrapper = new CategoryAllProductsWrapper();
        categoryWrapper.setCategories(categories);
        this.parser.fromObjectToXML(CategoryAllProductsWrapper.class, categoryWrapper, OutputFilePaths.CATEGORIES_BY_PRODUCT);
    }

    private void usersAndProducts() throws IOException, JAXBException {
        List<UserNamesWithAgeAndProducts> users = this.userService.getUserWhoSoldProductsWithAge();
        UsersProductsWrapper usersWrapper = new UsersProductsWrapper();
        usersWrapper.setUsers(users);
        this.parser.fromObjectToXML(UsersProductsWrapper.class, usersWrapper, OutputFilePaths.USERS_AND_PRODUCTS);
    }
}
