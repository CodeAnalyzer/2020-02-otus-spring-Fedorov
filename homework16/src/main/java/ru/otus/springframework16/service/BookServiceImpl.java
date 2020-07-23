package ru.otus.springframework16.service;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.springframework16.domain.Author;
import ru.otus.springframework16.domain.Book;
import ru.otus.springframework16.domain.Genre;
import ru.otus.springframework16.repository.AuthorRepository;
import ru.otus.springframework16.repository.BookRepository;
import ru.otus.springframework16.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final ShellService shellService;
    private final MessageService messageService;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, ShellService shellService, MessageService messageService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.shellService = shellService;
        this.messageService = messageService;
    }

    private Optional<Author> getAuthor(){
        Author author = null;
        String authorName = shellService.bookAuthorNameInput();
        List<Author> authors = authorRepository.findByName(authorName);
        if (authors.size() > 1) {
            messageService.messagePrintOut("book.error.foundToManyAuthors");
        } else
        if (authors.size() == 1) {
            author = authors.get(0);
        }
        if (authors.size() == 0){
            author = authorRepository.save(new Author(0L, authorName));
        }
        return Optional.ofNullable(author);
    }

    private Optional<Genre> getGenre(){
        Genre genre = null;
        String genreName = shellService.bookGenreNameInput();
        List<Genre> genres = genreRepository.findByName(genreName);
        if (genres.size() > 1) {
            messageService.messagePrintOut("book.error.foundToManyGenres");
        } else
        if (genres.size() == 1) {
            genre = genres.get(0);
        }
        if (genres.size() == 0){
            genre = genreRepository.save(new Genre(0L, genreName));
        }
        return Optional.ofNullable(genre);
    }

    @Override
    @Transactional
    public void insert() {
        Author author = getAuthor().orElse(null);
        if (author == null)
            return;

        Genre genre = getGenre().orElse(null);
        if (genre == null)
            return;

        String title = shellService.bookTitleInput();
        Book book = new Book(0L, title, author, genre);
        try{
            bookRepository.save(book);
            messageService.messagePrintOut("book.success.insert", new Object[] {book.getBookID(), book.getTitle()});
        } catch (Exception e){
            messageService.messagePrintOut("book.error.insert", e.getMessage());
        }
    }

    @Override
    @Transactional
    public void update() {
        Long bookID = shellService.bookIDInput();
        if (!bookRepository.existsById(bookID)){
            messageService.messagePrintOut("book.error.bookNotFound");
            return;
        }
        Book book = bookRepository.findById(bookID).orElse(null);
        shellService.bookOutput(book);

        Author author = getAuthor().orElse(null);
        if (author == null)
            return;

        Genre genre = getGenre().orElse(null);
        if (genre == null)
            return;

        String title = shellService.bookTitleInput();
        Book newBook = new Book(bookID, title, author, genre);
        try {
            bookRepository.save(newBook);
            messageService.messagePrintOut("book.success.update", new Object[] {newBook.getBookID(), newBook.getTitle()});
        } catch (Exception e){
            messageService.messagePrintOut("book.error.update", e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete() {
        Long bookID = shellService.bookIDInput();
        if (!bookRepository.existsById(bookID)){
            messageService.messagePrintOut("book.error.bookNotFound");
            return;
        }

        try{
            bookRepository.deleteById(bookID);
            messageService.messagePrintOut("book.success.delete", new Object[] {bookID});
        } catch (Exception e){
            messageService.messagePrintOut("book.error.delete", e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void findAll() {
        List<Book> list = bookRepository.findAll();
        shellService.bookListOutput(list);
    }
}
