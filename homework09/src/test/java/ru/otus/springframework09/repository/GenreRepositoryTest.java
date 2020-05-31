package ru.otus.springframework09.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import ru.otus.springframework09.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с жанрами")
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
    void shouldCorrectInsertGenre() {
        Genre genre  = new Genre(0L,  NEW_GENRE_NAME);
        genreRepository.save(genre);

        Optional<Genre> genreDB  = genreRepository.findById(NEW_GENRE_ID);

        assertThat(genreDB).isNotEmpty().get().hasFieldOrPropertyWithValue("genreID", NEW_GENRE_ID);
        assertThat(genreDB).isNotEmpty().get().hasFieldOrPropertyWithValue("name", NEW_GENRE_NAME);
    }

    @Test
    @DisplayName("должен корректно удалять из базы жанр")
    void shouldCorrectDeleteGenreByID() {
        Genre genre  = new Genre(DEFAULT_GENRE_ID,  DEFAULT_GENRE_NAME);
        genreRepository.delete(genre);
        assertThat(genreRepository.existsById(DEFAULT_GENRE_ID)).isEqualTo(false);
    }

    @Test
    @DisplayName("должен корректно искать жанр в базе по ID")
    void shouldCorrectFindGenreByID() {
        Optional<Genre> genreDB = genreRepository.findById(DEFAULT_GENRE_ID);
        assertThat(genreDB).isNotEmpty().get().hasFieldOrPropertyWithValue("genreID", DEFAULT_GENRE_ID);
        assertThat(genreDB).isNotEmpty().get().hasFieldOrPropertyWithValue("name", DEFAULT_GENRE_NAME);
    }

}
