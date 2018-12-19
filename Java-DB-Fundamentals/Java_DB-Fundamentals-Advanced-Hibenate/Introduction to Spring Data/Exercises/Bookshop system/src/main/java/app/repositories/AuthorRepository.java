package app.repositories;

import app.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(value = "SELECT CONCAT(a.first_name, ' ', a.last_name, ' ', COUNT(b.id)) FROM authors as a\n" +
            "JOIN books as b ON a.id = b.author_id\n" +
            "GROUP BY a.id ORDER BY COUNT(b.id) DESC;", nativeQuery = true)
    List<String> findAuthorsByOrOrderByBooksDesc();

    @Query(value = "SELECT CONCAT(a.first_name, ' ',  a.last_name) FROM authors as a\n" +
            "JOIN books AS b ON a.id = b.author_id\n" +
            "WHERE YEAR(b.release_date) < 1990;", nativeQuery = true)
    List<String> findAuthorsByBooksReleaseDateBefore1990();
}
