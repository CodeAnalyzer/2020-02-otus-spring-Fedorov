package ru.otus.springframework05.dao;

import ru.otus.springframework05.domain.Genre;
import ru.otus.springframework05.exception.GenreAlreadyExistsException;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    Genre insert(Genre genre) throws GenreAlreadyExistsException;
    void update(Genre genre);
    void delete(Long genreID);

    List<Genre> findAll();
    Optional<Genre> findByID(Long genreID);
    List<Genre> findByName(String name);
    boolean checkExists(Long genreID);
}
