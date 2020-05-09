package ru.otus.springframework06.repository;

import ru.otus.springframework06.domain.Genre;
import ru.otus.springframework06.exception.GenreAlreadyExistsException;
import ru.otus.springframework06.exception.GenreNotFoundException;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Genre insert (Genre genre) throws GenreAlreadyExistsException;
    Genre update (Genre genre) throws GenreNotFoundException;
    void delete (Genre genre) throws GenreNotFoundException;

    List<Genre> findAll();
    Optional<Genre> findByID(Long genrerID);
    List<Genre> findByName(String name);
    boolean checkExists(Genre genre);
}
