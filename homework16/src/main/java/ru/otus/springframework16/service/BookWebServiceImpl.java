package ru.otus.springframework16.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springframework16.domain.Author;
import ru.otus.springframework16.domain.Book;
import ru.otus.springframework16.domain.Genre;
import ru.otus.springframework16.exception.AuthorFoundTooManyException;
import ru.otus.springframework16.exception.GenreFoundTooManyException;
import ru.otus.springframework16.repository.AuthorRepository;
import ru.otus.springframework16.repository.BookRepository;
import ru.otus.springframework16.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookWebServiceImpl implements BookWebService{

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final MessageService messageService;

    public BookWebServiceImpl (BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, MessageService messageService){
        this.bookRepository   = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository  = genreRepository;
        this.messageService   = messageService;
    }

    private Optional<Author> getAuthor(String authorName){
        Author author = null;
        List<Author> authors = authorRepository.findByName(authorName);
        if (authors.size() > 1) {
            throw new AuthorFoundTooManyException(messageService.getMessage("book.error.foundToManyAuthors"));
        } else
        if (authors.size() == 1) {
            author = authors.get(0);
        }
        if (authors.size() == 0){
            author = authorRepository.save(new Author(0L, authorName));
        }
        return Optional.ofNullable(author);
    }

    private Optional<Genre> getGenre(String genreName){
        Genre genre = null;
        List<Genre> genres = genreRepository.findByName(genreName);
        if (genres.size() > 1) {
            throw new GenreFoundTooManyException(messageService.getMessage("book.error.foundToManyGenres"));
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
    public void insert(String authorName, String genreName, String bookTitle) {
        Author author = getAuthor(authorName).orElse(null);
        if (author == null)
            return;

        Genre genre = getGenre(genreName).orElse(null);
        if (genre == null)
            return;

        Book book = new Book(0L, bookTitle, author, genre);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void update(Book book) {
        Author author = getAuthor(book.getAuthor().getName()).orElse(null);
        if (author == null)
            return;

        Genre genre = getGenre(book.getGenre().getName()).orElse(null);
        if (genre == null)
            return;

        Book updBook = new Book(book.getBookID(), book.getTitle(), author, genre);
        bookRepository.save(updBook);
    }

    @Override
    @Transactional
    public void delete(Long bookID) {
        Book book = bookRepository.findById(bookID).get();
        bookRepository.delete(book);
    }

    @Override
    public Book findById(Long bookID) {
        return bookRepository.findById(bookID).get();
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
