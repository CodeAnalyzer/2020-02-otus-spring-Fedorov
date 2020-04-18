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
    private final AppSettings settings;

    public ShellServiceImpl(MessageSource messageSource, InputOutputService inputOutputService, AppSettings settings) {
        this.messageSource = messageSource;
        this.inputOutputService = inputOutputService;
        this.settings = settings;
    }

    @Override
    public void messagePrintOut(String messageID) {
        inputOutputService.printOut(messageSource.getMessage(messageID,null, settings.getLocale()));
    }

    @Override
    public void messagePrintOut(String messageID, String message) {
        inputOutputService.printOut(messageSource.getMessage(messageID,null, settings.getLocale()) + message);
    }

    @Override
    public void messagePrintOut(String messageID, Object[] objects) {
        inputOutputService.printOut(messageSource.getMessage(messageID, objects, settings.getLocale()));
    }

    public Author authorInsert() {
        messagePrintOut("author.name.input");
        String name = inputOutputService.readString();
        return new Author(0L, name);
    }

    public Author authorUpdate() {
        messagePrintOut("author.ID.input");
        Long id = inputOutputService.readLong();
        inputOutputService.readString();
        messagePrintOut("author.name.input");
        String name = inputOutputService.readString();
        return new Author(id, name);
    }

    public Author authorDelete() {
        messagePrintOut("author.ID.input");
        Long id = inputOutputService.readLong();
        return new Author(id, "");
    }


    @Override
    public void authorListOutput(List<Author> list) {
        if (list.size() == 0) {
            messagePrintOut("author.list.empty");
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
        messagePrintOut("genre.name.input");
        String name = inputOutputService.readString();
        return new Genre(0L, name);
    }

    @Override
    public Genre genreUpdate() {
        messagePrintOut("genre.ID.input");
        Long id = inputOutputService.readLong();
        inputOutputService.readString();
        messagePrintOut("genre.name.input");
        String name = inputOutputService.readString();
        return new Genre(id, name);
    }

    @Override
    public Genre genreDelete() {
        messagePrintOut("genre.ID.input");
        Long id = inputOutputService.readLong();
        return new Genre(id, "");
    }


    @Override
    public void genreListOutput(List<Genre> list) {
        if (list.size() == 0) {
            messagePrintOut("genre.list.empty");
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
        messagePrintOut("book.authorName.input");
        return inputOutputService.readString();
    }

    @Override
    public String bookGenreNameInput() {
        messagePrintOut("book.genreName.input");
        return inputOutputService.readString();
    }

    @Override
    public String bookTitleInput() {
        messagePrintOut("book.title.input");
        return inputOutputService.readString();
    }

    @Override
    public Long bookIDInput() {
        messagePrintOut("book.ID.input");
        Long bookID = inputOutputService.readLong();
        inputOutputService.readString();
        return bookID;
    }

    @Override
    public void bookListOutput(List<Book> list) {
        if (list.size() == 0) {
            messagePrintOut("book.list.empty");
            return;
        }

        for(int i = 0; i < list.size(); i++) {
            Book book = list.get(i);
            bookOutput(book);
        }
    }
}
