package ru.otus.springframework08.service;

import org.springframework.stereotype.Service;

import ru.otus.springframework08.domain.Author;
import ru.otus.springframework08.domain.Book;
import ru.otus.springframework08.domain.Comment;
import ru.otus.springframework08.domain.Genre;

import java.util.List;

@Service
public class ShellServiceImpl implements ShellService{

    private final InputOutputService inputOutputService;
    private final MessageService messageService;

    public ShellServiceImpl(InputOutputService inputOutputService, MessageService messageService) {
        this.inputOutputService = inputOutputService;
        this.messageService = messageService;
    }

    public Author authorInsert() {
        messageService.messagePrintOut("author.name.input");
        String name = inputOutputService.readString();
        return new Author("", name);
    }

    public Author authorUpdate() {
        messageService.messagePrintOut("author.ID.input");
        String id = inputOutputService.readString();
        messageService.messagePrintOut("author.name.input");
        String name = inputOutputService.readString();
        return new Author(id, name);
    }

    public Author authorDelete() {
        messageService.messagePrintOut("author.ID.input");
        String id = inputOutputService.readString();
        return new Author(id, "");
    }

    @Override
    public void authorListOutput(List<Author> list) {
        if (list.size() == 0) {
            messageService.messagePrintOut("author.list.empty");
            return;
        }

        for(int i = 0; i < list.size(); i++) {
            Author author = list.get(i);
            String message = messageService.getMessage("author.ID.output")
                    + author.getAuthorID() + "; "
                    + messageService.getMessage("author.name.output")
                    + author.getName();
            inputOutputService.printOut(message);
        }
    }

    @Override
    public Genre genreInsert() {
        messageService.messagePrintOut("genre.name.input");
        String name = inputOutputService.readString();
        return new Genre("", name);
    }

    @Override
    public Genre genreUpdate() {
        messageService.messagePrintOut("genre.ID.input");
        String id = inputOutputService.readString();
        messageService.messagePrintOut("genre.name.input");
        String name = inputOutputService.readString();
        return new Genre(id, name);
    }

    @Override
    public Genre genreDelete() {
        messageService.messagePrintOut("genre.ID.input");
        String id = inputOutputService.readString();
        return new Genre(id, "");
    }

    @Override
    public void genreListOutput(List<Genre> list) {
        if (list.size() == 0) {
            messageService.messagePrintOut("genre.list.empty");
            return;
        }

        for(int i = 0; i < list.size(); i++) {
            Genre genre = list.get(i);
            String message = messageService.getMessage("genre.ID.output")
                    + genre.getGenreID() + "; "
                    + messageService.getMessage("genre.name.output")
                    + genre.getName();
            inputOutputService.printOut(message);
        }
    }

    @Override
    public String commentRemarkInput() {
        messageService.messagePrintOut("comment.remark.input");
        String remark = inputOutputService.readString();
        return remark;
    }

    @Override
    public String commentIDInput() {
        messageService.messagePrintOut("comment.ID.input");
        String commentID = inputOutputService.readString();
        return commentID;
    }

    @Override
    public void commentListOutput(List<Comment> list) {
        if (list.size() == 0) {
            messageService.messagePrintOut("comment.list.empty");
            return;
        }

        for(int i = 0; i < list.size(); i++) {
            Comment comment = list.get(i);
            String message = messageService.getMessage("comment.ID.output")
                    + comment.getCommentID() + "; "
                    + messageService.getMessage("comment.remark.output")
                    + comment.getRemark();
            inputOutputService.printOut(message);
        }
    }

    @Override
    public void bookOutput(Book book) {
        String message = messageService.getMessage("book.ID.output")
                + book.getBookID() + "; "
                + messageService.getMessage("book.title.output")
                + book.getTitle() + "; "
                + messageService.getMessage("book.authorName.output")
                + book.getAuthor().getName() + "; "
                + messageService.getMessage("book.genreName.output")
                + book.getGenre().getName()+ "; ";
        inputOutputService.printOut(message);
    }

    @Override
    public String bookAuthorNameInput() {
        messageService.messagePrintOut("book.authorName.input");
        return inputOutputService.readString();
    }

    @Override
    public String bookGenreNameInput() {
        messageService.messagePrintOut("book.genreName.input");
        return inputOutputService.readString();
    }

    @Override
    public String bookTitleInput() {
        messageService.messagePrintOut("book.title.input");
        return inputOutputService.readString();
    }

    @Override
    public String bookIDInput() {
        messageService.messagePrintOut("book.ID.input");
        String bookID = inputOutputService.readString();
        return bookID;
    }

    @Override
    public void bookListOutput(List<Book> list) {
        if (list.size() == 0) {
            messageService.messagePrintOut("book.list.empty");
            return;
        }

        for(int i = 0; i < list.size(); i++) {
            Book book = list.get(i);
            bookOutput(book);
        }
    }
}
