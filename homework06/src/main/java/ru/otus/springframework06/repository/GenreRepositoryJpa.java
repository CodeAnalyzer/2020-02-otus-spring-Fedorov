package ru.otus.springframework06.repository;

import org.springframework.stereotype.Repository;
import ru.otus.springframework06.domain.Genre;
import ru.otus.springframework06.exception.GenreAlreadyExistsException;
import ru.otus.springframework06.exception.GenreNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryJpa implements GenreRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Genre insert(Genre genre) throws GenreAlreadyExistsException {
        if (checkExists(genre)){
            throw new GenreAlreadyExistsException("Жанр " + genre.getName() + " уже добавлен в базу!");
        }
        entityManager.persist(genre);
        return genre;
    }

    @Override
    public Genre update(Genre genre) throws GenreNotFoundException {
        if (!checkExists(genre)) {
            throw new GenreNotFoundException("Жанр c ID="+genre.getGenreID()+" не найден в базе!");
        }
        return entityManager.merge(genre);
    }

    @Override
    public void delete(Genre genre) throws GenreNotFoundException {
        if (!checkExists(genre)) {
            throw new GenreNotFoundException("Жанр c ID="+genre.getGenreID()+" не найден в базе!");
        }
        Query query = entityManager.createQuery("delete from Genre g where g.genreID = :genreID");
        query.setParameter("genreID", genre.getGenreID());
        query.executeUpdate();
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = entityManager.createQuery(
                "select g from Genre g",
                Genre.class);
        return query.getResultList();
    }

    @Override
    public Optional<Genre> findByID(Long genreID) {
        return Optional.of(entityManager.find(Genre.class, genreID));
    }

    @Override
    public List<Genre> findByName(String name) {
        TypedQuery<Genre> query = entityManager.createQuery(
                "select g from Genre g where g.name = :name",
                Genre.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public boolean checkExists(Genre genre) {
        Long cnt = 0L;
        if (genre.getGenreID() != 0L){
            TypedQuery<Long> query = entityManager.createQuery(
                    "select count(g) from Genre g where g.genreID = :genreID",
                    Long.class);
            query.setParameter("genreID", genre.getGenreID());
            cnt = query.getSingleResult();
        }
        else{
            TypedQuery<Long> query = entityManager.createQuery(
                    "select count(g) from Genre g where g.name = :name",
                    Long.class);
            query.setParameter("name", genre.getName());
            cnt = query.getSingleResult();
        }
        return cnt > 0 ;
    }
}
