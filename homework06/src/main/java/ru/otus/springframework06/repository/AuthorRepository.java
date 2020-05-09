package ru.otus.springframework06.repository;

import ru.otus.springframework06.domain.Author;
import ru.otus.springframework06.exception.*;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Author insert (Author author) throws AuthorAlreadyExistsException;
    Author update (Author author) throws AuthorNotFoundException;
    void delete (Author author) throws AuthorNotFoundException;

    List<Author> findAll();
    Optional<Author> findByID(Long authorID);
    List<Author> findByName(String name);
    boolean checkExists(Author author);
}
