package ru.otus.springframework05.dao;

import ru.otus.springframework05.domain.Book;
import ru.otus.springframework05.exception.*;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Book insert(Book book) throws BookAlreadyExistsException;
    void update(Book book) throws BookNotFoundException;
    void delete(Book book) throws BookNotFoundException;

    List<Book> findAll();
    Optional<Book> findByID(Long bookID);
    List<Book> findBookByParam(Long authorID, Long genreID, String title);

    boolean checkExists(Long bookID);
    boolean checkExists(Book book);
}
