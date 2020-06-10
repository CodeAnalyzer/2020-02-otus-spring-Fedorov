package ru.otus.springframework09.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springframework09.domain.Book;

import java.util.List;

@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional(readOnly = true)
    @EntityGraph(value = "BookWithAuthorAndGenre")
    List<Book> findAll();
}
