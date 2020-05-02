package ru.otus.springframework06.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springframework06.domain.Book;
import ru.otus.springframework06.exception.BookAlreadyExistsException;
import ru.otus.springframework06.exception.BookNotFoundException;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class BookRepositoryJpa implements BookRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(Book book) throws BookAlreadyExistsException {
        entityManager.persist(book);
    }

    @Override
    public void update(Book book) throws BookNotFoundException {
        entityManager.merge(book);
    }

    @Override
    public void delete(Book book) throws BookNotFoundException {
        TypedQuery<Book> query = entityManager.createQuery(
                "select b from Book b where b.bookID = :bookID",
                Book.class);
        query.setParameter("bookID", book.getBookID());
        Book bookDB =  query.getSingleResult();
        entityManager.remove(bookDB);
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("BookWithAuthorAndGenre");
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public Optional<Book> findByID(Long bookID) {
        return Optional.of(entityManager.find(Book.class, bookID));
    }

    @Override
    public boolean checkExists(Book book) {
        Long cnt = 0L;
        if (book.getBookID() != 0L){
            TypedQuery<Long> query = entityManager.createQuery(
                    "select count(b) from Book b where b.bookID = :bookID",
                    Long.class);
            query.setParameter("bookID", book.getBookID());
            cnt = query.getSingleResult();
        }
        else{
            TypedQuery<Long> query = entityManager.createQuery(
                    "select count(b) from Book b where b.title = :title and authorID = :authorID and genreID = :genreID",
                    Long.class);
            query.setParameter("title", book.getTitle());
            query.setParameter("authorID", book.getAuthor().getAuthorID());
            query.setParameter("genreID", book.getGenre().getGenreID());
            cnt = query.getSingleResult();
        }
        return cnt > 0 ;
    }
}
