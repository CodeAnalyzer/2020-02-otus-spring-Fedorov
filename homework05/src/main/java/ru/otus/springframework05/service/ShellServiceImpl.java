package ru.otus.springframework05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import ru.otus.springframework05.config.AppSettings;
import ru.otus.springframework05.domain.Author;
import ru.otus.springframework05.domain.Book;
import ru.otus.springframework05.domain.Genre;

import java.util.List;

@Service
public class ShellServiceImpl implements ShellService{

    private final MessageSource messageSource;
    private final InputOutputService inputOutputService;

    @Autowired
    private AppSettings settings;

    public ShellServiceImpl(MessageSource messageSource, InputOutputService inputOutputService) {
        this.messageSource = messageSource;
        this.inputOutputService = inputOutputService;
    }

    public Author authorInsert() {
        inputOutputService.printOut(messageSource.getMessage("author.name.input",null, settings.getLocale()));
        String name = inputOutputService.readString();
        return new Author(0L, name);
    }

    public Author authorUpdate() {
        inputOutputService.printOut(messageSource.getMessage("author.ID.input",null, settings.getLocale()));
        Long id = inputOutputService.readLong();
        inputOutputService.readString();
        inputOutputService.printOut(messageSource.getMessage("author.name.input",null, settings.getLocale()));
        String name = inputOutputService.readString();
        return new Author(id, name);
    }

    public Long authorDelete() {
        inputOutputService.printOut(messageSource.getMessage("author.ID.input",null, settings.getLocale()));
        Long id = inputOutputService.readLong();
        return id;
    }

    public void authorInsertError(String message) {
        inputOutputService.printOut(messageSource.getMessage("author.error.insert",null, settings.getLocale()) + message);
    }

    public void authorUpdateError() {
        inputOutputService.printOut(messageSource.getMessage("author.error.update",null, settings.getLocale()));
    }

    public void authorDeleteError() {
        inputOutputService.printOut(messageSource.getMessage("author.error.delete",null, settings.getLocale()));
    }

    @Override
    public void authorInsertSuccess(Author author) {
        String message = messageSource.getMessage("author.success.insert",null, settings.getLocale());
        message = String.format(message, author.getAuthorID(), author.getName());
        inputOutputService.printOut(message);
    }

    @Override
    public void authorUpdateSuccess(Author author) {
        String message = messageSource.getMessage("author.success.update",null, settings.getLocale());
        message = String.format(message, author.getAuthorID(), author.getName());
        inputOutputService.printOut(message);
    }

    @Override
    public void authorDeleteSuccess(Long authorID) {
        String message = messageSource.getMessage("author.success.delete",null, settings.getLocale());
        message = String.format(message, authorID);
        inputOutputService.printOut(message);
    }

    @Override
    public void authorListOutput(List<Author> list) {
        if (list.size() == 0) {
            inputOutputService.printOut(messageSource.getMessage("author.list.empty",null, settings.getLocale()));
            return;
        }

        for(int i = 0; i < list.size(); i++) {
            Author author = list.get(i);
            String message = messageSource.getMessage("author.ID.output",null, settings.getLocale())
                    + String.valueOf(author.getAuthorID()) + "; "
                    + messageSource.getMessage("author.name.output",null, settings.getLocale())
                    + author.getName();
            inputOutputService.printOut(message);
        }
    }

    @Override
    public Genre genreInsert() {
        inputOutputService.printOut(messageSource.getMessage("genre.name.input",null, settings.getLocale()));
        String name = inputOutputService.readString();
        return new Genre(0L, name);
    }

    @Override
    public Genre genreUpdate() {
        inputOutputService.printOut(messageSource.getMessage("genre.ID.input",null, settings.getLocale()));
        Long id = inputOutputService.readLong();
        inputOutputService.readString();
        inputOutputService.printOut(messageSource.getMessage("genre.name.input",null, settings.getLocale()));
        String name = inputOutputService.readString();
        return new Genre(id, name);
    }

    @Override
    public Long genreDelete() {
        inputOutputService.printOut(messageSource.getMessage("genre.ID.input",null, settings.getLocale()));
        Long id = inputOutputService.readLong();
        return id;
    }

    @Override
    public void genreInsertError(String message) {
        inputOutputService.printOut(messageSource.getMessage("genre.error.insert",null, settings.getLocale()) + message);
    }

    @Override
    public void genreUpdateError() {
        inputOutputService.printOut(messageSource.getMessage("genre.error.update",null, settings.getLocale()));
    }

    @Override
    public void genreDeleteError() {
        inputOutputService.printOut(messageSource.getMessage("genre.error.delete",null, settings.getLocale()));
    }

    @Override
    public void genreInsertSuccess(Genre genre) {
        String message = messageSource.getMessage("genre.success.insert",null, settings.getLocale());
        message = String.format(message, genre.getGenreID(), genre.getName());
        inputOutputService.printOut(message);
    }

    @Override
    public void genreUpdateSuccess(Genre genre) {
        String message = messageSource.getMessage("genre.success.update",null, settings.getLocale());
        message = String.format(message, genre.getGenreID(), genre.getName());
        inputOutputService.printOut(message);
    }

    @Override
    public void genreDeleteSuccess(Long genreID) {
        String message = messageSource.getMessage("genre.success.delete",null, settings.getLocale());
        message = String.format(message, genreID);
        inputOutputService.printOut(message);
    }

    @Override
    public void genreListOutput(List<Genre> list) {
        if (list.size() == 0) {
            inputOutputService.printOut(messageSource.getMessage("genre.list.empty",null, settings.getLocale()));
            return;
        }

        for(int i = 0; i < list.size(); i++) {
            Genre genre = list.get(i);
            String message = messageSource.getMessage("genre.ID.output",null, settings.getLocale())
                    + String.valueOf(genre.getGenreID()) + "; "
                    + messageSource.getMessage("genre.name.output",null, settings.getLocale())
                    + genre.getName();
            inputOutputService.printOut(message);
        }
    }

    @Override
    public void bookOutput(Book book) {
        String message = messageSource.getMessage("book.ID.output",null, settings.getLocale())
                + String.valueOf(book.getBookID()) + "; "
                + messageSource.getMessage("book.title.output",null, settings.getLocale())
                + book.getTitle() + "; "
                + messageSource.getMessage("book.authorName.output",null, settings.getLocale())
                + book.getAuthor().getName() + "; "
                + messageSource.getMessage("book.genreName.output",null, settings.getLocale())
                + book.getGenre().getName()+ "; ";
        inputOutputService.printOut(message);
    }

    @Override
    public String bookAuthorNameInput() {
        inputOutputService.printOut(messageSource.getMessage("book.authorName.input",null, settings.getLocale()));
        return inputOutputService.readString();
    }

    @Override
    public String bookGenreNameInput() {
        inputOutputService.printOut(messageSource.getMessage("book.genreName.input",null, settings.getLocale()));
        return inputOutputService.readString();
    }

    @Override
    public String bookTitleInput() {
        inputOutputService.printOut(messageSource.getMessage("book.title.input",null, settings.getLocale()));
        return inputOutputService.readString();
    }

    @Override
    public Long bookIDInput() {
        inputOutputService.printOut(messageSource.getMessage("book.ID.input",null, settings.getLocale()));
        Long bookID = inputOutputService.readLong();
        inputOutputService.readString();
        return bookID;
    }

    @Override
    public void bookInsertError(String message) {
        inputOutputService.printOut(messageSource.getMessage("book.error.insert",null, settings.getLocale()) + message);
    }

    @Override
    public void bookUpdateError() {
        inputOutputService.printOut(messageSource.getMessage("book.error.update",null, settings.getLocale()));
    }

    @Override
    public void bookDeleteError() {
        inputOutputService.printOut(messageSource.getMessage("book.error.delete",null, settings.getLocale()));
    }

    @Override
    public void bookInsertSuccess(Book book) {
        String message = messageSource.getMessage("book.success.insert",null, settings.getLocale());
        message = String.format(message, book.getBookID(), book.getTitle());
        inputOutputService.printOut(message);
    }

    @Override
    public void bookUpdateSuccess(Book book) {
        String message = messageSource.getMessage("book.success.update",null, settings.getLocale());
        message = String.format(message, book.getBookID(), book.getTitle());
        inputOutputService.printOut(message);
    }

    @Override
    public void bookDeleteSuccess(Long bookID) {
        String message = messageSource.getMessage("book.success.delete",null, settings.getLocale());
        message = String.format(message, bookID);
        inputOutputService.printOut(message);
    }

    @Override
    public void bookFoundToManyAuthors() {
        inputOutputService.printOut(messageSource.getMessage("book.error.foundToManyAuthors",null, settings.getLocale()));
    }

    @Override
    public void bookFoundToManyGenres() {
        inputOutputService.printOut(messageSource.getMessage("book.error.foundToManyGenres",null, settings.getLocale()));
    }

    @Override
    public void bookNotFound() {
        inputOutputService.printOut(messageSource.getMessage("book.error.bookNotFound",null, settings.getLocale()));
    }

    @Override
    public void bookListOutput(List<Book> list) {
        if (list.size() == 0) {
            inputOutputService.printOut(messageSource.getMessage("book.list.empty",null, settings.getLocale()));
            return;
        }

        for(int i = 0; i < list.size(); i++) {
            Book book = list.get(i);
            bookOutput(book);
        }
    }
}
