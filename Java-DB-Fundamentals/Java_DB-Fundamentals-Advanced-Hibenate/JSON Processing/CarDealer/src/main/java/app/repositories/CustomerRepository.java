package app.repositories;

import app.models.dtos.views.CustomerTotalSales;
import app.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer AS c ORDER BY c.birthDate, c.isYoungDriver")
    List<Customer> findAllOrOrderByBirthDateAndIsYoungDriver();

    @Query("SELECT c FROM Customer AS c " +
            "WHERE c.id IN (SELECT s.customer FROM Sale As s)")
    List<Customer> getAllCustomersWithSales();
}
