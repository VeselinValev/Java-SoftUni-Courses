package app.repositories;

import app.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User AS u " +
            "JOIN Product AS p ON p.seller = u.id " +
                    "WHERE p.buyer IS NOT NULL " +
                    "GROUP BY u.id ORDER BY u.lastName, u.firstName")
    List<User> getAllWhoSoldProducts();

    @Query("SELECT u FROM User AS u " +
            "JOIN Product AS p ON p.seller = u.id " +
            "WHERE p.buyer IS NOT NULL " +
            "GROUP BY u.id ORDER BY u.soldProducts.size, u.lastName")
    List<User> getAllUsersWhoSoldProducts();


}
