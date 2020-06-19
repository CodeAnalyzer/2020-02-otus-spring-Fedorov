package ru.otus.springframework10.service;

import ru.otus.springframework10.domain.Book;
import java.util.List;

public interface BookWebService {
    Book insert(String authorName, String genreName, String bookTitle);
    Book update(Book book);
    void delete(Long bookID);
    Book findById(Long bookID);
    List<Book> findAll();
}
