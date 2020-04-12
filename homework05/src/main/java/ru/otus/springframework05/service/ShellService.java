package ru.otus.springframework05.service;

import ru.otus.springframework05.domain.Author;
import ru.otus.springframework05.domain.Book;
import ru.otus.springframework05.domain.Genre;

import java.util.List;

public interface ShellService {

    // Shell service for Author

    Author authorInsert();
    Author authorUpdate();
    Long authorDelete();

    void authorInsertError(String message);
    void authorUpdateError();
    void authorDeleteError();

    void authorInsertSuccess(Author author);
    void authorUpdateSuccess(Author author);
    void authorDeleteSuccess(Long authorID);

    void authorListOutput(List<Author> list);

    // Shell service for Genre

    Genre genreInsert();
    Genre genreUpdate();
    Long genreDelete();

    void genreInsertError(String message);
    void genreUpdateError();
    void genreDeleteError();

    void genreInsertSuccess(Genre genre);
    void genreUpdateSuccess(Genre genre);
    void genreDeleteSuccess(Long genreID);

    void genreListOutput(List<Genre> list);

    // Shell service for Book

    void bookOutput(Book book);

    String bookAuthorNameInput();
    String bookGenreNameInput();
    String bookTitleInput();
    Long bookIDInput();

    void bookInsertError(String message);
    void bookUpdateError();
    void bookDeleteError();

    void bookInsertSuccess(Book book);
    void bookUpdateSuccess(Book book);
    void bookDeleteSuccess(Long bookID);

    void bookFoundToManyAuthors();
    void bookFoundToManyGenres();
    void bookNotFound();

    void bookListOutput(List<Book> list);
}
