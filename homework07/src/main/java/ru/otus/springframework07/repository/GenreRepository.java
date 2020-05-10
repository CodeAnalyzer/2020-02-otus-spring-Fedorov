package ru.otus.springframework07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springframework07.domain.Genre;
import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findByName(String name);
}
