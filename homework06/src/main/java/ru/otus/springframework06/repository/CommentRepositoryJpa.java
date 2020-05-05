package ru.otus.springframework06.repository;

import org.springframework.stereotype.Repository;
import ru.otus.springframework06.domain.Comment;
import ru.otus.springframework06.domain.Genre;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpa implements CommentRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Comment insert(Comment comment) {
        return entityManager.merge(comment);
    }

    @Override
    public Comment update(Comment comment) {
        return entityManager.merge(comment);
    }

    @Override
    public void delete(Comment comment){
        entityManager.remove(comment);
    }

    @Override
    public List<Comment> findByBookID(Long bookID) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("CommentsWithBook");
        TypedQuery<Comment> query = entityManager.createQuery(
                "select c from Comment c join fetch c.book b where b.bookID = :bookID", Comment.class);
        query.setParameter("bookID", bookID);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public Optional<Comment> findByID(Long commentID) {
        return Optional.of(entityManager.find(Comment.class, commentID));
    }
}
