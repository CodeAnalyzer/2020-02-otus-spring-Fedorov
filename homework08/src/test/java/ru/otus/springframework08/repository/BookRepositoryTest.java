package ru.otus.springframework08.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.springframework08.domain.Author;
import ru.otus.springframework08.domain.Book;
import ru.otus.springframework08.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с книгами")
@SpringBootTest
class BookRepositoryTest {

    private static final String DEFAULT_BOOK_ID = "1";
    private static final String DEFAULT_BOOK_TITLE = "Tom Sawyer";

    private static final String NEW_BOOK_ID = "2";
    private static final String NEW_BOOK_TITLE = "Huckleberry Finn";

    private static final String DEFAULT_AUTHOR_ID = "1";
    private static final String DEFAULT_AUTHOR_NAME = "Mark Twain";

    private static final String DEFAULT_GENRE_ID = "1";
    private static final String DEFAULT_GENRE_NAME = "Novel";

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("должен корректно добавлять в базу книгу")
    void shouldCorrectInsertBook(){
        Book newBook = new Book(NEW_BOOK_ID, NEW_BOOK_TITLE,
                new Author(DEFAULT_AUTHOR_ID, DEFAULT_AUTHOR_NAME),
                new Genre(DEFAULT_GENRE_ID, DEFAULT_GENRE_NAME));
        bookRepository.insert(newBook);

        Optional<Book> bookDB  = bookRepository.findById(NEW_BOOK_ID);
        assertThat(bookDB).isNotEmpty().get().isEqualTo(newBook);
    }

    @Test
    @DisplayName("должен корректно удалять из базы книгу")
    void shouldCorrectDeleteBookByID() {
        Book book  = new Book(DEFAULT_BOOK_ID, DEFAULT_BOOK_TITLE,
                new Author(DEFAULT_AUTHOR_ID, DEFAULT_AUTHOR_NAME),
                new Genre(DEFAULT_GENRE_ID, DEFAULT_GENRE_NAME));

        bookRepository.delete(book);
        assertThat(bookRepository.existsById(DEFAULT_BOOK_ID)).isEqualTo(false);
    }

    @Test
    @DisplayName("должен корректно искать книгу в базе по ID")
    void shouldCorrectFindBookByID() {
        Book newBook = new Book(DEFAULT_BOOK_ID, DEFAULT_BOOK_TITLE,
                new Author(DEFAULT_AUTHOR_ID, DEFAULT_AUTHOR_NAME),
                new Genre(DEFAULT_GENRE_ID, DEFAULT_GENRE_NAME));
        bookRepository.insert(newBook);

        Optional<Book> bookDB = bookRepository.findById(DEFAULT_BOOK_ID);
        assertThat(bookDB).isNotEmpty().get().hasFieldOrPropertyWithValue("bookID", DEFAULT_BOOK_ID);
        assertThat(bookDB).isNotEmpty().get().hasFieldOrPropertyWithValue("title", DEFAULT_BOOK_TITLE);
    }

}
