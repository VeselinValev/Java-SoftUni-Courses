package app.services.api;

import app.entities.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthorsWithNamesEndingWith(String endOfName);

    List<Author> getAuthorByBookCopies();

    Integer getBookCountByAuthor(String firstName, String lastName);
}
