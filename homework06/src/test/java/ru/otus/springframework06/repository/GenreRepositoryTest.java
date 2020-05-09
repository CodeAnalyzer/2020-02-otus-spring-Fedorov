package ru.otus.springframework06.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.springframework06.SpringFramework06Application;
import ru.otus.springframework06.domain.Genre;
import ru.otus.springframework06.exception.GenreAlreadyExistsException;
import ru.otus.springframework06.exception.GenreNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с жанрами")
@Import({GenreRepositoryJpa.class})
@ContextConfiguration(classes= SpringFramework06Application.class)
@DataJpaTest
public class GenreRepositoryTest {

    private static final Long DEFAULT_GENRE_ID = 1L;
    private static final String DEFAULT_GENRE_NAME = "Novel";

    private static final Long NEW_GENRE_ID = 2L;
    private static final String NEW_GENRE_NAME = "Comedy";

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("должен корректно добавлять в базу жанр")
    void shouldCorrectInsertGenre() throws GenreAlreadyExistsException {
        Genre genre  = new Genre(0L,  NEW_GENRE_NAME);
        genreRepository.insert(genre);

        Optional<Genre> genreDB  = genreRepository.findByID(NEW_GENRE_ID);

        assertThat(genreDB).isNotEmpty().get().hasFieldOrPropertyWithValue("genreID", NEW_GENRE_ID);
        assertThat(genreDB).isNotEmpty().get().hasFieldOrPropertyWithValue("name", NEW_GENRE_NAME);
    }

    @Test
    @DisplayName("должен корректно удалять из базы жанр")
    void shouldCorrectDeleteGenreByID() throws GenreNotFoundException {
        Genre genre  = new Genre(DEFAULT_GENRE_ID,  DEFAULT_GENRE_NAME);
        genreRepository.delete(genre);
        assertThat(genreRepository.checkExists(genre)).isEqualTo(false);
    }

    @Test
    @DisplayName("должен корректно искать жанр в базе по ID")
    void shouldCorrectFindGenreByID() {
        Optional<Genre> genreDB = genreRepository.findByID(DEFAULT_GENRE_ID);
        assertThat(genreDB).isNotEmpty().get().hasFieldOrPropertyWithValue("genreID", DEFAULT_GENRE_ID);
        assertThat(genreDB).isNotEmpty().get().hasFieldOrPropertyWithValue("name", DEFAULT_GENRE_NAME);
    }

}
