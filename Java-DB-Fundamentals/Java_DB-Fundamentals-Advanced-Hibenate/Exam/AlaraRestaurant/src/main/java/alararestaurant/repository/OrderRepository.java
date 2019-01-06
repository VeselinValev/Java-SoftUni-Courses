package alararestaurant.repository;

import alararestaurant.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM alararestaurant.domain.entities.Order AS o " +
            "JOIN alararestaurant.domain.entities.Employee AS e ON o.employee = e.id " +
            "JOIN alararestaurant.domain.entities.Position AS p ON e.position = p.id " +
            "WHERE p.name = 'Burger Flipper' " +
            "ORDER BY e.name, o.id")
    List<Order> getAllByEmployeePosition();
}
