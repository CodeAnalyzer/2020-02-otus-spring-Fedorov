package ru.otus.springframework12.service;

import ru.otus.springframework12.domain.Book;
import java.util.List;

public interface BookWebService {
    void insert(String authorName, String genreName, String bookTitle);
    void update(Book book);
    void delete(Long bookID);
    Book findById(Long bookID);
    List<Book> findAll();
}
