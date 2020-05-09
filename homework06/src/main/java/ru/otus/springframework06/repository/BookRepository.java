package ru.otus.springframework06.repository;


import ru.otus.springframework06.domain.Book;
import ru.otus.springframework06.exception.BookAlreadyExistsException;
import ru.otus.springframework06.exception.BookNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    void insert (Book book) throws BookAlreadyExistsException;
    void update (Book book) throws BookNotFoundException;
    void delete (Book book) throws BookNotFoundException;

    List<Book> findAll();
    Optional<Book> findByID(Long bookID);
    boolean checkExists(Book book);
    Long getCount();
}
