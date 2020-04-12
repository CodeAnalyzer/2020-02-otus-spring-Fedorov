package ru.otus.springframework05.dao;

import ru.otus.springframework05.domain.Book;
import ru.otus.springframework05.exception.BookAlreadyExistsException;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Book insert(Book book) throws BookAlreadyExistsException;
    void update(Book book);
    void delete(Long bookID);

    List<Book> findAll();
    Optional<Book> findByID(Long bookID);
    List<Book> findBookByParam(Long authorID, Long genreID, String title);
    boolean checkExists(Long bookID);
}
