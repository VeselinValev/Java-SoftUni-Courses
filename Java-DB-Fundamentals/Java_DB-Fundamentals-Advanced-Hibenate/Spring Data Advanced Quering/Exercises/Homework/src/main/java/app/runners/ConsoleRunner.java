package app.runners;

import app.entities.Author;
import app.entities.Book;
import app.entities.Category;
import app.enums.AgeRestriction;
import app.enums.EditionType;
import app.services.api.AuthorService;
import app.services.api.BookService;
import app.services.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final BookService bookService;

    private final AuthorService authorService;

    private final CategoryService categoryService;

    @Autowired
    public ConsoleRunner(BookService bookService, AuthorService authorService, CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
//        booksTitlesByAgeRestriction("minor");
//        goldenBooks(EditionType.GOLD, 5000);
//        booksByPrice(BigDecimal.valueOf(5), BigDecimal.valueOf(40));
//        notReleasedBooks(1998);
//        booksReleasedBeforeDate("12-04-1992");
//        authorsSearch("dy");
//        bookSearch("WOR");
//        bookTitlesSearch("gr");
//        countBooks(12);
//        totalBookCopies();
//        reducedBook("Absalom");
//        increaseBookCopies("12-Oct-2005", 100);
//        removeBooks(4200);
//        storedProcedure("Amanda Rice");
    }

    private void booksTitlesByAgeRestriction(String ageRestriction){
        this.bookService.getBooksWithGivenAgeRestriction(ageRestriction)
                .forEach(x -> System.out.println(x.getTitle()));
    }

    private void goldenBooks(EditionType editionType, int copies){
        this.bookService.getGoldenEditionBooksLessThanCopies(editionType, copies)
                .forEach(x -> System.out.println(x.getTitle()));
    }

    private void booksByPrice(BigDecimal underPrice, BigDecimal overPrice){
        this.bookService.getBooksInPriceRange(underPrice, overPrice)
                .forEach(x -> System.out.println(x.getTitle() + " - " + x.getPrice()));
    }

    private void notReleasedBooks(int year){
        this.bookService.getBooksNotReleasedInYear(year)
                .forEach(x -> System.out.println(x.getTitle()));
    }

    private void booksReleasedBeforeDate(String dateAsString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
        Date date = format.parse(dateAsString);
        this.bookService.getBooksReleasedBeforeDate(date)
                .forEach(x -> System.out.println(x.getTitle().trim() + " " + x.getEditionType() + " " + x.getPrice()));
    }

    private void authorsSearch(String endOfName){
        this.authorService.getAuthorsWithNamesEndingWith(endOfName)
                .forEach(x -> System.out.println(x.getFirstName() + " " + x.getLastName()));
    }

    private void bookSearch(String word){
        this.bookService.getBooksWithTitleContainingWord(word)
                .forEach(x -> System.out.println(x.getTitle()));
    }

    private void bookTitlesSearch(String word){
        this.bookService.getBooksWithAuthorLastNameStartsWith(word)
                .forEach(x -> System.out.println(x.getTitle() +
                        " (" + x.getAuthor().getFirstName() + " " + x.getAuthor().getLastName() + ")"));
    }

    private void countBooks(int number){
        System.out.println(this.bookService.getBooksByTitleLengthLongerThanNumber(number));
    }

    private void totalBookCopies(){
        this.authorService.getAuthorByBookCopies()
                .forEach(x -> System.out.printf("%s %s %s%n", x.getFirstName(), x.getLastName(), x.getCopies()));
    }

    private void reducedBook(String title){
        System.out.println(this.bookService.getBookByTitle(title));
    }

    private void increaseBookCopies(String dateAsString, int copies) throws IOException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        Date date = format.parse(dateAsString);

        System.out.println(this.bookService.increaseBookCopiesAfterDate(copies, date));
    }

    private void removeBooks(int copies){
        System.out.printf("%d books were deleted%n", this.bookService.removeBooksWithCopiesLessThan(copies));
    }

    private void storedProcedure(String authorName){
        String firstName = authorName.split(" ")[0];
        String lastName = authorName.split(" ")[1];
        int numberOfBooks = this.authorService.getBookCountByAuthor(firstName, lastName);
        if (numberOfBooks > 0){
            System.out.printf("%s has written %d books%n", authorName, numberOfBooks);
        }else{
            System.out.printf("W%s has not written any books yet%n", authorName);
        }
    }
}
