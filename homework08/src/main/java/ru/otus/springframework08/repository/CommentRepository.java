package ru.otus.springframework08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import ru.otus.springframework08.domain.Comment;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
public interface CommentRepository extends MongoRepository<Comment, String> {

    @Transactional(readOnly = true)
    @Query(value = "{'book.bookID': ?0}")
    List<Comment> findByBookID(String bookID);
}
