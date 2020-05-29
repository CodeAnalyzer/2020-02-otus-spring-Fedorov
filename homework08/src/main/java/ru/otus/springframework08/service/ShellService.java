package ru.otus.springframework08.service;

import org.springframework.lang.Nullable;
import ru.otus.springframework08.domain.Author;
import ru.otus.springframework08.domain.Book;
import ru.otus.springframework08.domain.Comment;
import ru.otus.springframework08.domain.Genre;

import java.util.List;

public interface ShellService {

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

    // Shell service for Comment

    String commentRemarkInput();
    String commentIDInput();

    void commentListOutput(List<Comment> list);

    // Shell service for Book

    String bookAuthorNameInput();
    String bookGenreNameInput();
    String bookTitleInput();
    String bookIDInput();

    void bookOutput(Book book);
    void bookListOutput(List<Book> list);

}
