package ru.otus.springframework08.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.springframework08.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с жанрами")
@SpringBootTest
public class GenreRepositoryTest {

    private static final String DEFAULT_GENRE_ID = "1";
    private static final String DEFAULT_GENRE_NAME = "Novel";

    private static final String NEW_GENRE_ID = "2";
    private static final String NEW_GENRE_NAME = "Comedy";

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("должен корректно добавлять в базу жанр")
    void shouldCorrectInsertGenre() {
        Genre genre  = new Genre(NEW_GENRE_ID,  NEW_GENRE_NAME);
        genreRepository.save(genre);

        Optional<Genre> genreDB  = genreRepository.findById(NEW_GENRE_ID);
        assertThat(genreDB).isNotEmpty().get().isEqualTo(genre);
    }


    @Test
    @DisplayName("должен корректно искать жанр в базе по ID")
    void shouldCorrectFindGenreByID() {
        Genre genre  = new Genre(DEFAULT_GENRE_ID,  DEFAULT_GENRE_NAME);
        genreRepository.save(genre);

        Optional<Genre> genreDB = genreRepository.findById(DEFAULT_GENRE_ID);

        assertThat(genreDB).isNotEmpty().get().hasFieldOrPropertyWithValue("genreID", DEFAULT_GENRE_ID);
        assertThat(genreDB).isNotEmpty().get().hasFieldOrPropertyWithValue("name", DEFAULT_GENRE_NAME);
    }

    @Test
    @DisplayName("должен корректно удалять из базы жанр")
    void shouldCorrectDeleteGenreByID()  {
        Genre genre  = new Genre(NEW_GENRE_ID,  NEW_GENRE_NAME);
        genreRepository.save(genre);
        assertThat(genreRepository.existsById(NEW_GENRE_ID)).isEqualTo(true);

        genreRepository.delete(genre);
        assertThat(genreRepository.existsById(NEW_GENRE_ID)).isEqualTo(false);
    }
}
