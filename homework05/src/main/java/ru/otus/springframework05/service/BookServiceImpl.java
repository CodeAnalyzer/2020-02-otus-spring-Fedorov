package ru.otus.springframework05.service;

import org.springframework.stereotype.Service;

import ru.otus.springframework05.dao.AuthorDao;
import ru.otus.springframework05.dao.BookDao;
import ru.otus.springframework05.dao.GenreDao;
import ru.otus.springframework05.domain.Author;
import ru.otus.springframework05.domain.Book;
import ru.otus.springframework05.domain.Genre;
import ru.otus.springframework05.exception.AuthorAlreadyExistsException;
import ru.otus.springframework05.exception.BookAlreadyExistsException;
import ru.otus.springframework05.exception.GenreAlreadyExistsException;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final ShellService shellService;

    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao, ShellService shellService) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.shellService = shellService;
    }

    @Override
    public void insert() {
        Author author = null;
        String authorName = shellService.bookAuthorNameInput();
        List<Author> authors = authorDao.findByName(authorName);
        if (authors.size() > 1) {
            shellService.bookFoundToManyAuthors();
            return;
        } else
        if (authors.size() == 1) {
            author = authors.get(0);
        }
        if (authors.size() == 0){
            try {
                author = authorDao.insert(new Author(0L, authorName));
            } catch (AuthorAlreadyExistsException e) {
                shellService.authorInsertError(e.getMessage());
            }
        }

        Genre genre = null;
        String genreName = shellService.bookGenreNameInput();
        List<Genre> genres = genreDao.findByName(genreName);
        if (genres.size() > 1) {
            shellService.bookFoundToManyGenres();
            return;
        } else
        if (genres.size() == 1) {
            genre = genres.get(0);
        }
        if (genres.size() == 0){
            try {
                genre = genreDao.insert(new Genre(0L, genreName));
            } catch (GenreAlreadyExistsException e) {
                shellService.genreInsertError(e.getMessage());
            }
        }

        String title = shellService.bookTitleInput();
        Book book = new Book(0L, title, author, genre);
        try {
            bookDao.insert(book);
            shellService.bookInsertSuccess(book);
        } catch (BookAlreadyExistsException e){
            shellService.bookInsertError(e.getMessage());
        }
    }

    @Override
    public void update() {
        Long bookID = shellService.bookIDInput();
        if (!bookDao.checkExists(bookID)){
            shellService.bookNotFound();
            return;
        }
        Book book = bookDao.findByID(bookID).orElse(null);
        shellService.bookOutput(book);

        Author author = null;
        String authorName = shellService.bookAuthorNameInput();
        List<Author> authors = authorDao.findByName(authorName);
        if (authors.size() > 1) {
            shellService.bookFoundToManyAuthors();
            return;
        } else
        if (authors.size() == 1) {
            author = authors.get(0);
        }
        if (authors.size() == 0){
            try {
                author = authorDao.insert(new Author(0L, authorName));
            } catch (AuthorAlreadyExistsException e) {
                shellService.authorInsertError(e.getMessage());
            }
        }

        Genre genre = null;
        String genreName = shellService.bookGenreNameInput();
        List<Genre> genres = genreDao.findByName(genreName);
        if (genres.size() > 1) {
            shellService.bookFoundToManyGenres();
            return;
        } else
        if (genres.size() == 1) {
            genre = genres.get(0);
        }
        if (genres.size() == 0){
            try {
                genre = genreDao.insert(new Genre(0L, genreName));
            } catch (GenreAlreadyExistsException e) {
                shellService.genreInsertError(e.getMessage());
            }
        }

        String title = shellService.bookTitleInput();
        Book newBook = new Book(bookID, title, author, genre);
        try {
            bookDao.update(newBook);
            shellService.bookUpdateSuccess(newBook);
        } catch (Exception e){
            shellService.bookUpdateError();
        }
    }

    @Override
    public void delete() {
        Long bookID = shellService.bookIDInput();
        if (!bookDao.checkExists(bookID)){
            shellService.bookNotFound();
            return;
        }
        bookDao.delete(bookID);
        shellService.bookDeleteSuccess(bookID);
    }

    @Override
    public void findAll() {
        List<Book> list = bookDao.findAll();
        shellService.bookListOutput(list);
    }
}
