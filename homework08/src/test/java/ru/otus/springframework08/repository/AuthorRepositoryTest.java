package ru.otus.springframework08.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.springframework08.domain.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с авторами")
@SpringBootTest
class AuthorRepositoryTest {

    private static final String DEFAULT_AUTHOR_ID = "1";
    private static final String DEFAULT_AUTHOR_NAME = "Mark Twain";

    private static final String NEW_AUTHOR_ID = "2";
    private static final String NEW_AUTHOR_NAME = "Jack London";

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("должен корректно добавлять в базу автора")
    void shouldCorrectInsertAuthor() {
        Author author  = new Author(NEW_AUTHOR_ID,  NEW_AUTHOR_NAME);
        authorRepository.insert(author);

        Optional<Author> authorDB = authorRepository.findById(NEW_AUTHOR_ID);
        assertThat(authorDB).isNotEmpty().get().isEqualTo(author);
    }

    @Test
    @DisplayName("должен корректно искать автора в базе по ID")
    void shouldCorrectFindAuthorByID() {
        Author author  = new Author(DEFAULT_AUTHOR_ID,  DEFAULT_AUTHOR_NAME);
        authorRepository.save(author);

        Optional<Author> authorDB = authorRepository.findById(DEFAULT_AUTHOR_ID);

        assertThat(authorDB).isNotEmpty().get().hasFieldOrPropertyWithValue("authorID", DEFAULT_AUTHOR_ID);
        assertThat(authorDB).isNotEmpty().get().hasFieldOrPropertyWithValue("name", DEFAULT_AUTHOR_NAME);
    }

    @Test
    @DisplayName("должен корректно удалять из базы автора")
    void shouldCorrectDeleteAuthorByID() {
        Author author  = new Author(DEFAULT_AUTHOR_ID,  DEFAULT_AUTHOR_NAME);
        authorRepository.delete(author);
        assertThat(authorRepository.existsById(DEFAULT_AUTHOR_ID)).isEqualTo(false);
    }

}