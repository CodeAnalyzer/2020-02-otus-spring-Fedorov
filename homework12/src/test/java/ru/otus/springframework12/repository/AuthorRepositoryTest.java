package ru.otus.springframework12.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.springframework12.domain.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с авторами")
@DataJpaTest
class AuthorRepositoryTest {

    private static final Long DEFAULT_AUTHOR_ID = 1L;
    private static final String DEFAULT_AUTHOR_NAME = "Mark Twain";

    private static final Long NEW_AUTHOR_ID = 2L;
    private static final String NEW_AUTHOR_NAME = "Jack London";

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("должен корректно добавлять в базу автора")
    void shouldCorrectInsertAuthor() {
        Author author  = new Author(0L,  NEW_AUTHOR_NAME);
        authorRepository.save(author);

        Optional<Author> authorDB  = authorRepository.findById(NEW_AUTHOR_ID);

        assertThat(authorDB).isNotEmpty().get().hasFieldOrPropertyWithValue("authorID", NEW_AUTHOR_ID);
        assertThat(authorDB).isNotEmpty().get().hasFieldOrPropertyWithValue("name", NEW_AUTHOR_NAME);
    }

    @Test
    @DisplayName("должен корректно удалять из базы автора")
    void shouldCorrectDeleteAuthorByID() {
        Author author  = new Author(DEFAULT_AUTHOR_ID,  DEFAULT_AUTHOR_NAME);
        authorRepository.delete(author);
        assertThat(authorRepository.existsById(DEFAULT_AUTHOR_ID)).isEqualTo(false);
    }

    @Test
    @DisplayName("должен корректно искать автора в базе по ID")
    void shouldCorrectFindAuthorByID() {
        Optional<Author> authorDB = authorRepository.findById(DEFAULT_AUTHOR_ID);
        assertThat(authorDB).isNotEmpty().get().hasFieldOrPropertyWithValue("authorID", DEFAULT_AUTHOR_ID);
        assertThat(authorDB).isNotEmpty().get().hasFieldOrPropertyWithValue("name", DEFAULT_AUTHOR_NAME);
    }
}