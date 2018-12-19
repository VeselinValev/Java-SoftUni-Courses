package app.services.impl;

import app.dto.ReducedBook;
import app.entities.Book;
import app.enums.AgeRestriction;
import app.enums.EditionType;
import app.repositories.BookRepository;
import app.services.api.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

@Service
@Transactional
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public List<Book> getBooksWithGivenAgeRestriction(String ageRestriction) {
        return this.bookRepository.getAllByAgeRestrictionEquals(AgeRestriction.valueOf(ageRestriction.toUpperCase()));
    }

    @Override
    public List<Book> getGoldenEditionBooksLessThanCopies(EditionType editionType, int copies) {
        return this.bookRepository.getAllByEditionTypeAndCopiesLessThan(editionType, copies);
    }

    @Override
    public List<Book> getBooksInPriceRange(BigDecimal underPrice, BigDecimal overPrice) {
        return this.bookRepository.getAllByPriceLessThanOrPriceGreaterThan(underPrice, overPrice);
    }

    @Override
    public List<Book> getBooksNotReleasedInYear(int year) {
        return this.bookRepository.getAllByReleaseYearNotEqualTo(String.valueOf(year));
    }

    @Override
    public List<Book> getBooksReleasedBeforeDate(Date date) {
        return this.bookRepository.getAllByReleaseDateBefore(date);
    }

    @Override
    public List<Book> getBooksWithTitleContainingWord(String word) {
        return this.bookRepository.getAllByTitleContains(word);
    }

    @Override
    public List<Book> getBooksWithAuthorLastNameStartsWith(String word) {
        return this.bookRepository.getAllByTitleAndAuthorLastNameStartsWith(word);
    }

    @Override
    public int getBooksByTitleLengthLongerThanNumber(int number) {
        return this.bookRepository.getAllByTitleLengthGreaterThan(number).size();
    }

    @Override
    public String getBookByTitle(String title) {
        Book book = this.bookRepository.findFirstByTitle(title);
        ModelMapper modelMapper = new ModelMapper();
        ReducedBook reducedBook = modelMapper.map(book, ReducedBook.class);
        return String.format("%s %s %s %.2f%n", book.getTitle().trim(), book.getEditionType(), book.getAgeRestriction(), book.getPrice());
    }

    @Override
    public int increaseBookCopiesAfterDate(int copies, Date date) {
        this.bookRepository.increaseBookCopiesAfterDate(copies, date);
        return this.bookRepository.getAllByReleaseDateAfter(date).size() * copies;
    }

    @Override
    public int removeBooksWithCopiesLessThan(int copies) {
        int numberOfRemovedBooks = this.bookRepository.getAllByCopiesLessThan(copies).size();
        this.bookRepository.deleteAllByCopiesLessThan(copies);
        return numberOfRemovedBooks;
    }
}
