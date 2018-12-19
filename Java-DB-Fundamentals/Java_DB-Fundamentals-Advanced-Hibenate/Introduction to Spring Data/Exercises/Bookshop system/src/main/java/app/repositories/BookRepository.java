package app.repositories;

import app.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT b.title FROM books b WHERE year(b.release_date) > '2000'", nativeQuery = true)
    List<String> findAllBooksAfter2000();

    @Query(value = "SELECT CONCAT(b.title, ' ', DATE(b.release_date), ' ', b.copies) FROM authors as a \n" +
            "JOIN books as b ON a.id = b.author_id AND b.author_id = 4\n" +
            "ORDER BY b.id, b.release_date DESC, b.title ASC", nativeQuery = true)
    List<String> findBooksByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc();
}
