package ru.otus.springframework05.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import ru.otus.springframework05.domain.Author;
import ru.otus.springframework05.exception.*;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            Long authorID  = resultSet.getLong("authorID");
            String name    = resultSet.getString("name");
            return new Author(authorID, name);
        }
    }


    @Override
    public Author insert(Author author) throws AuthorAlreadyExistsException {
        if (checkExists(author)){
            throw new AuthorAlreadyExistsException("Автор " + author.getName() + " уже добавлен в базу!");
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", author.getName());
        GeneratedKeyHolder key = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(
                "insert into author (name) values (:name)", params, key
        );
        author.setAuthorID((Long) key.getKey());

        return author;
    }

    @Override
    public void update(Author author) throws AuthorNotFoundException{
        if (!checkExists(author)) {
            throw new AuthorNotFoundException("Автор c ID="+author.getAuthorID()+" не найден в базе!");
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("authorID", author.getAuthorID());
        params.addValue("name", author.getName());
        namedParameterJdbcOperations.update(
                "update author set name = :name where authorID = :authorID", params
        );
    }

    @Override
    public void delete(Author author) throws AuthorNotFoundException{
        if (!checkExists(author)) {
            throw new AuthorNotFoundException("Автор c ID="+author.getAuthorID()+" не найден в базе!");
        }

        Map<String, Object> params = Collections.singletonMap("authorID", author.getAuthorID());
        namedParameterJdbcOperations.update(
                "delete from author where authorID = :authorID", params
        );
    }

    @Override
    public List<Author> findAll() {
        return namedParameterJdbcOperations.getJdbcOperations().query(
                "select * from author", new AuthorMapper()
        );
    }

    @Override
    public Optional<Author> findByID(Long authorID) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("authorID", authorID);
        Author res = namedParameterJdbcOperations.queryForObject(
                "select * from author where authorID = :authorID", params, new AuthorMapper()
        );
        return Optional.ofNullable(res);
    }

    @Override
    public List<Author> findByName(String name) {
        String sql = String.format("select * from author where name = '%s'", name);
        List<Author> res = namedParameterJdbcOperations.getJdbcOperations().query(sql, new AuthorMapper());
        return res;
    }

    @Override
    public boolean checkExists(Author author) {
        int res = 0;
        MapSqlParameterSource params = new MapSqlParameterSource();
        if (author.getAuthorID() != 0){
            params.addValue("authorID", author.getAuthorID());
            res =  namedParameterJdbcOperations.queryForObject(
                    "select count(*) from author where authorID = :authorID", params, Integer.class
            );
        } else {
            params.addValue("name", author.getName());
            res =  namedParameterJdbcOperations.queryForObject(
                    "select count(*) from author where name = :name", params, Integer.class
            );
        }
        return res>0;
    }
}
