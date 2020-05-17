package ru.otus.springframework08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.springframework08.domain.Author;
import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String>  {

    List<Author> findByName(String name);
}
