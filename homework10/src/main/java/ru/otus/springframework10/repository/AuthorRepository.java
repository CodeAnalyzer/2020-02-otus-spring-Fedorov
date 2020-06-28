package ru.otus.springframework10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springframework10.domain.Author;
import java.util.List;

@Transactional
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Transactional(readOnly = true)
    @Query("select a from Author a where a.name = :name")
    List<Author> findByName(@Param("name") String name);
}
