package ru.otus.springframework06.repository;

import ru.otus.springframework06.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment insert (Comment comment);
    Comment update (Comment comment);
    void  delete (Comment comment);

    List<Comment> findByBookID(Long bookID);
    Optional<Comment> findByID(Long commentID);

}
