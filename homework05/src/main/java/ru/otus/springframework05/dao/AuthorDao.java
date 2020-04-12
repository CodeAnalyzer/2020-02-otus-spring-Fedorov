package ru.otus.springframework05.dao;

import ru.otus.springframework05.domain.Author;
import ru.otus.springframework05.exception.AuthorAlreadyExistsException;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    Author insert(Author author) throws AuthorAlreadyExistsException;
    void update(Author author);
    void delete(Long authorID);

    List<Author> findAll();
    Optional<Author> findByID(Long authorID);
    List<Author> findByName(String name);
    boolean checkExists(Long authorID);
}
