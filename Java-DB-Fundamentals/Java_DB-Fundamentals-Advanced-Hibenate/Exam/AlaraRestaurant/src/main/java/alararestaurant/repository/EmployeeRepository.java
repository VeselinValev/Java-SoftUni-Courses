package alararestaurant.repository;

import alararestaurant.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Retention;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee getByName(String name);

    @Query("SELECT e FROM alararestaurant.domain.entities.Employee AS e " +
            "JOIN alararestaurant.domain.entities.Position AS p ON e.position = p.id " +
            "WHERE p.name = 'Burger Flipper' " +
            "ORDER BY e.name")
    List<Employee> getAllByEmployeePosition();
}
