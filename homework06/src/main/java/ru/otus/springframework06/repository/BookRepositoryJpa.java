package ru.otus.springframework06.repository;

import org.springframework.stereotype.Repository;
import ru.otus.springframework06.domain.Book;
import ru.otus.springframework06.exception.BookAlreadyExistsException;
import ru.otus.springframework06.exception.BookNotFoundException;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpa implements BookRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(Book book) throws BookAlreadyExistsException {
        if (checkExists(book)){
            throw new BookAlreadyExistsException("Книга " + book.getTitle() + " уже добавлена в базу!");
        }
        entityManager.persist(book);
    }

    @Override
    public void update(Book book) throws BookNotFoundException {
        if (!checkExists(book)){
            throw new BookNotFoundException("Книга " + book.getTitle() + " не найдена в базе!");
        }
        entityManager.merge(book);
    }

    @Override
    public void delete(Book book) throws BookNotFoundException {
        if (!checkExists(book)){
            throw new BookNotFoundException("Книга " + book.getTitle() + " не найдена в базе!");
        }
        Query query = entityManager.createQuery("delete from Book b where b.bookID = :bookID");
        query.setParameter("bookID", book.getBookID());
        query.executeUpdate();
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
        TypedQuery<Book> query = entityManager.createQuery(
                "select b from Book b where b.bookID = :bookID",
                Book.class);
        query.setParameter("bookID", bookID);
        return Optional.ofNullable(query.getSingleResult());
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

    @Override
    public Long getCount() {
        TypedQuery<Long> query = entityManager.createQuery("select count(b) from Book b", Long.class);
        return query.getSingleResult();
    }
}
