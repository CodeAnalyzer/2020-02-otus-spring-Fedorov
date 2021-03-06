package ru.otus.springframework09.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.springframework09.domain.Comment;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Transactional(readOnly = true)
    @Query("select c from Comment c join fetch c.book b where b.bookID = :bookID")
    List<Comment> findByBookID(@Param("bookID") Long bookID);
}
