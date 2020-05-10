package ru.otus.springframework07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springframework07.domain.Author;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByName(String name);
}
