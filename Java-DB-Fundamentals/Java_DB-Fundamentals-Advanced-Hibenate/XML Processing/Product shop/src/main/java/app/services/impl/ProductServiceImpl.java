package app.services.impl;

import app.models.dtos.binding.ProductDto;
import app.models.dtos.views.ProductNamePriceSellerName;
import app.models.entities.Product;
import app.models.entities.User;
import app.repositories.ProductRepository;
import app.services.api.ProductService;
import app.services.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private UserService userService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.modelMapper = new ModelMapper();
        this.userService = userService;
    }

    @Override
    public void saveAll(List<ProductDto> products) {
        Random random = new Random();
        List<User> users = this.userService.findAllUsers();
        for (ProductDto userDto : products) {
            Product product = this.modelMapper.map(userDto, Product.class);
            product.setSeller(users.get(random.nextInt(users.size())));
            if (random.nextInt(6) != 5) { // leaves some of the products without buyers
                while (product.getBuyer() == product.getSeller() || product.getBuyer() == null) {
                    product.setBuyer(users.get(random.nextInt(users.size())));
                }
            }
            this.productRepository.saveAndFlush(product);
        }
    }

    @Override
    public Product getRandomEntity() {
        return this.productRepository.getRandomEntity();
    }


    @Override
    public List<ProductNamePriceSellerName> getAllProductsAndConvertToDto(BigDecimal priceFrom, BigDecimal priceTo) {
        List<Product> products = this.productRepository.getAllByPriceBetweenAndBuyerIsNullOrderByPrice(priceFrom, priceTo);
        List<ProductNamePriceSellerName> dtoProducts = new ArrayList<>();
        for (Product product : products) {
            String sellerName = product.getSeller().getFirstName() == null ? "" : product.getSeller().getFirstName() + " ";
            sellerName += product.getSeller().getLastName();
            ProductNamePriceSellerName dtoProduct = new ProductNamePriceSellerName();
            dtoProduct.setName(product.getName());
            dtoProduct.setPrice(product.getPrice());
            dtoProduct.setSeller(sellerName);
            dtoProducts.add(dtoProduct);
        }
        return dtoProducts;
    }
}
