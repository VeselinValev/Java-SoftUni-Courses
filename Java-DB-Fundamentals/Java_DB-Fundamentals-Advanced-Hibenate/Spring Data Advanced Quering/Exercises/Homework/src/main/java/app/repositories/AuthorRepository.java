package app.repositories;

import app.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> getAllByFirstNameEndingWith(String endOfName);

    @Query("SELECT a FROM Author AS a " +
            "JOIN a.books AS b ON b.author = a " +
            "GROUP BY a.id ORDER BY SUM(b.copies) DESC")
    List<Author> getAllByTotalBookCopies();

    @Procedure(name = "ufn_calculate_book_count_by_author")
    Integer getBookCountByAuthor(@Param("first_name") String firstName, @Param("last_name") String lastName);
}
