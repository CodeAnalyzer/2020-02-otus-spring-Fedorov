package ru.otus.springframework05.service;

import org.springframework.lang.Nullable;
import ru.otus.springframework05.domain.Author;
import ru.otus.springframework05.domain.Book;
import ru.otus.springframework05.domain.Genre;

import java.util.List;

public interface ShellService {

    // Common service
    void messagePrintOut(String messageID);
    void messagePrintOut(String messageID, String message);
    void messagePrintOut(String messageID, @Nullable Object[] objects);

    // Shell service for Author

    Author authorInsert();
    Author authorUpdate();
    Author authorDelete();

    void authorListOutput(List<Author> list);

    // Shell service for Genre

    Genre genreInsert();
    Genre genreUpdate();
    Genre genreDelete();

    void genreListOutput(List<Genre> list);

    // Shell service for Book

    String bookAuthorNameInput();
    String bookGenreNameInput();
    String bookTitleInput();
    Long bookIDInput();

    void bookOutput(Book book);
    void bookListOutput(List<Book> list);
}
