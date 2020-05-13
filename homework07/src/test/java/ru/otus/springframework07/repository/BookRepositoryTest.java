package ru.otus.springframework07.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.springframework07.domain.Author;
import ru.otus.springframework07.domain.Book;
import ru.otus.springframework07.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с книгами")
@DataJpaTest
class BookRepositoryTest {

    private static final Long DEFAULT_BOOK_ID = 1L;
    private static final String DEFAULT_BOOK_TITLE = "Tom Sawyer";

    private static final Long NEW_BOOK_ID = 2L;
    private static final String NEW_BOOK_TITLE = "Huckleberry Finn";

    private static final Long DEFAULT_AUTHOR_ID = 1L;
    private static final String DEFAULT_AUTHOR_NAME = "Mark Twain";

    private static final Long DEFAULT_GENRE_ID = 1L;
    private static final String DEFAULT_GENRE_NAME = "Novel";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("должен корректно добавлять в базу книгу")
    void shouldCorrectInsertBook(){
        Book newBook = new Book(0L, NEW_BOOK_TITLE,
                new Author(DEFAULT_AUTHOR_ID, DEFAULT_AUTHOR_NAME),
                new Genre(DEFAULT_GENRE_ID, DEFAULT_GENRE_NAME));

        bookRepository.save(newBook);

        Optional<Book> bookDB  = bookRepository.findById(NEW_BOOK_ID);
        assertThat(bookDB).isNotEmpty().get().hasFieldOrPropertyWithValue("bookID", NEW_BOOK_ID);
        assertThat(bookDB).isNotEmpty().get().hasFieldOrPropertyWithValue("title", NEW_BOOK_TITLE);
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
        Optional<Book> bookDB = bookRepository.findById(DEFAULT_BOOK_ID);
        assertThat(bookDB).isNotEmpty().get().hasFieldOrPropertyWithValue("bookID", DEFAULT_BOOK_ID);
        assertThat(bookDB).isNotEmpty().get().hasFieldOrPropertyWithValue("title", DEFAULT_BOOK_TITLE);
    }

}
