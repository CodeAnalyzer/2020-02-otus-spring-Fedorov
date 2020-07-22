package ru.otus.springframework16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springframework16.domain.Genre;
import java.util.List;

@Transactional
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Transactional(readOnly = true)
    @Query("select g from Genre g where g.name = :name")
    List<Genre> findByName(String name);
}
