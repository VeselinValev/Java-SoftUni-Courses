package app.services.api;

import app.dto.ReducedBook;
import app.entities.Book;
import app.enums.AgeRestriction;
import app.enums.EditionType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface BookService {
    List<Book> getBooksWithGivenAgeRestriction(String ageRestriction);

    List<Book> getGoldenEditionBooksLessThanCopies(EditionType editionType, int copies);

    List<Book> getBooksInPriceRange(BigDecimal underPrice, BigDecimal overPrice);

    List<Book> getBooksNotReleasedInYear(int year);

    List<Book> getBooksReleasedBeforeDate(Date date);

    List<Book> getBooksWithTitleContainingWord(String word);

    List<Book> getBooksWithAuthorLastNameStartsWith(String word);

    int getBooksByTitleLengthLongerThanNumber(int number);

    String getBookByTitle(String title);

    int increaseBookCopiesAfterDate(int copies, Date date);

    int removeBooksWithCopiesLessThan(int copies);
}
