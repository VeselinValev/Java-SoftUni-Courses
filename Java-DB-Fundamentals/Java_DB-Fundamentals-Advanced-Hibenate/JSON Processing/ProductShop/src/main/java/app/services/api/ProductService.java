package app.services.api;

import app.models.dtos.binding.ProductDto;
import app.models.dtos.views.ProductNamePriceSellerName;
import app.models.entities.Product;
import app.models.entities.User;

import java.math.BigDecimal;
import java.util.List;


public interface ProductService {
    void saveAll(ProductDto[] products);

    Product getRandomEntity();

    List<ProductNamePriceSellerName> getAllProductsAndConvertToDto(BigDecimal priceFrom, BigDecimal priceTo);
}
