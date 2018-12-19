package app.repositories;

import app.model.enums.Size;
import app.model.shampoos.BasicShampoo;
import app.model.shampoos.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<BasicShampoo, Long> {
    List<Shampoo> getAllBySizeOrderById(Size size);
}
