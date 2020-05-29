package ru.otus.springframework08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springframework08.domain.Book;

import java.util.List;

@Transactional
public interface BookRepository extends MongoRepository<Book, String> {

    @Transactional(readOnly = true)
    List<Book> findAll();

    @Transactional(readOnly = true)
    @Query(value = "{'genre.genreID': ?0}")
    List<Book> findByGenreID(String genreID);

    @Transactional(readOnly = true)
    @Query(value = "{'author.authorID': ?0}")
    List<Book> findByAuthorID(String authorID);
}
