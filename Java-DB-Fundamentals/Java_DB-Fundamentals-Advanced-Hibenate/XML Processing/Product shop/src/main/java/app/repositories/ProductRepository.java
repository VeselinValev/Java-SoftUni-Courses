package app.repositories;

import app.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.security.PermitAll;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM products ORDER BY RAND () LIMIT 1", nativeQuery = true)
    Product getRandomEntity();

    List<Product> getAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal priceFrom, BigDecimal priceTo);
}
