package app.services.impl;

import app.entities.Author;
import app.repositories.AuthorRepository;
import app.services.api.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAuthorsWithNamesEndingWith(String endOfName) {
        return this.authorRepository.getAllByFirstNameEndingWith(endOfName);
    }

    @Override
    public List<Author> getAuthorByBookCopies() {
        return this.authorRepository.getAllByTotalBookCopies();
    }

    @Override
    public Integer getBookCountByAuthor(String firstName, String lastName) {
        return this.authorRepository.getBookCountByAuthor(firstName, lastName);
    }

}
