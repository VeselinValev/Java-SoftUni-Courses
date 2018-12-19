package app.repositories;

import app.dto.ReducedBook;
import app.entities.Book;
import app.enums.AgeRestriction;
import app.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> getAllByAgeRestrictionEquals(AgeRestriction ageRestriction);

    List<Book> getAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> getAllByPriceLessThanOrPriceGreaterThan(BigDecimal underPrice, BigDecimal overPrice);

    @Query("SELECT b FROM Book AS b WHERE SUBSTRING(b.releaseDate, 1, 4) <> :year")
    List<Book> getAllByReleaseYearNotEqualTo(@Param("year") String Year);

    List<Book> getAllByReleaseDateBefore(Date date);

    @Query("SELECT b FROM Book AS b WHERE b.title LIKE %:word%")
    List<Book> getAllByTitleContains(@Param("word") String word);

    @Query("SELECT b FROM Book AS b " +
            "WHERE b.author.lastName LIKE :word%")
    List<Book> getAllByTitleAndAuthorLastNameStartsWith(@Param("word") String word);

    @Query("SELECT b FROM Book AS b " +
            "WHERE LENGTH(b.title) > :number")
    List<Book> getAllByTitleLengthGreaterThan(@Param("number") int number);

    Book findFirstByTitle(String title);

    @Modifying
    @Transactional
    @Query("UPDATE Book AS b SET b.copies = b.copies + :copies " +
            "WHERE b.releaseDate > :date")
    void increaseBookCopiesAfterDate(@Param("copies") int copies, @Param("date") Date date);

    List<Book> getAllByReleaseDateAfter(Date date);

    List<Book> getAllByCopiesLessThan(int copies);

    @Modifying
    @Transactional
    void deleteAllByCopiesLessThan(int copies);
}
