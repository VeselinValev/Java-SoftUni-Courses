package app.services.api;

import app.entities.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {
    Book findByID(Long id);

    void remove(Book object);

    List<Book> findAll();

    void save(Book object);

    List<String> findAllBooksAfter2000();

    List<String> findBooksByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateAndTitle();
}
