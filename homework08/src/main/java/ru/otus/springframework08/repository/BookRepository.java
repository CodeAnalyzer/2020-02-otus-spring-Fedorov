package ru.otus.springframework08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.springframework08.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAll();
}
