package ru.otus.springframework06.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springframework06.domain.Author;
import ru.otus.springframework06.exception.AuthorAlreadyExistsException;
import ru.otus.springframework06.exception.AuthorNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AuthorRepositoryJpa implements AuthorRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author insert(Author author) throws AuthorAlreadyExistsException {
        if (checkExists(author)){
            throw new AuthorAlreadyExistsException("Автор " + author.getName() + " уже добавлен в базу!");
        }
        entityManager.persist(author);
        return author;
    }

    @Override
    public Author update(Author author) throws AuthorNotFoundException {
        if (!checkExists(author)) {
            throw new AuthorNotFoundException("Автор c ID="+author.getAuthorID()+" не найден в базе!");
        }
        return entityManager.merge(author);
    }

    @Override
    public void delete(Author author) throws AuthorNotFoundException {
        if (!checkExists(author)) {
            throw new AuthorNotFoundException("Автор c ID="+author.getAuthorID()+" не найден в базе!");
        }
        TypedQuery<Author> query = entityManager.createQuery(
                "select a from Author a where a.authorID = :authorID",
                Author.class);
        query.setParameter("authorID", author.getAuthorID());
        Author authorDB =  query.getSingleResult();
        entityManager.remove(authorDB);
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = entityManager.createQuery(
                "select a from Author a",
                Author.class);
        return query.getResultList();
    }

    @Override
    public Optional<Author> findByID(Long authorID) {
        return Optional.of(entityManager.find(Author.class, authorID));
    }

    @Override
    public List<Author> findByName(String name) {
        TypedQuery<Author> query = entityManager.createQuery(
                "select a from Author a where a.name = :name",
                Author.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public boolean checkExists(Author author) {
        Long cnt = 0L;
        if (author.getAuthorID() != 0L){
            TypedQuery<Long> query = entityManager.createQuery(
                    "select count(a) from Author a where a.authorID = :authorID",
                    Long.class);
            query.setParameter("authorID", author.getAuthorID());
            cnt = query.getSingleResult();
        }
        else{
            TypedQuery<Long> query = entityManager.createQuery(
                    "select count(a) from Author a where a.name = :name",
                    Long.class);
            query.setParameter("name", author.getName());
            cnt = query.getSingleResult();
        }
        return cnt > 0 ;
    }
}
