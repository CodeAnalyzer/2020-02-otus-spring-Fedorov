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
import ru.otus.springframework05.domain.Book;
import ru.otus.springframework05.domain.Genre;
import ru.otus.springframework05.exception.BookAlreadyExistsException;

@Repository
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Long bookID       = resultSet.getLong("bookID");
            String title      = resultSet.getString("title");
            Long genreID      = resultSet.getLong("genre.GenreID");
            String genreName  = resultSet.getString("genre.Name");
            Long authorID     = resultSet.getLong("author.AuthorID");
            String authorName = resultSet.getString("author.Name");
            return new Book(bookID, title, new Author(authorID, authorName), new Genre(genreID, genreName));
        }
    }

    @Override
    public Book insert(Book book) throws BookAlreadyExistsException {
        if (findBookByParam(book.getAuthor().getAuthorID(), book.getGenre().getGenreID(), book.getTitle()).size() > 0){
            throw new BookAlreadyExistsException(" Книга " + book.getTitle() + " уже добавлена в базу!");
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("genreID", book.getGenre().getGenreID());
        params.addValue("authorID", book.getAuthor().getAuthorID());
        GeneratedKeyHolder key = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(
                "insert into book (title, genreID, authorID) values (:title, :genreID, :authorID)", params, key
        );
        book.setBookID((Long) key.getKey());

        return book;
    }

    @Override
    public void update(Book book) {
        if (checkExists(book.getBookID())){
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("bookID", book.getBookID());
            params.addValue("title", book.getTitle());
            params.addValue("genreID", book.getGenre().getGenreID());
            params.addValue("authorID", book.getAuthor().getAuthorID());

            int res = namedParameterJdbcOperations.update(
                    "update book set title = :title, genreID = :genreID, authorID = :authorID where bookID = :bookID", params
            );
        }
    }

    @Override
    public void delete(Long bookID) {
        if (checkExists(bookID)){
            Map<String, Object> params = Collections.singletonMap("bookID", bookID);
            namedParameterJdbcOperations.update(
                    "delete from book where bookID = :bookID", params
            );
        }
    }

    @Override
    public List<Book> findAll() {
        return namedParameterJdbcOperations.getJdbcOperations().query(
                "select book.bookID, book.title, author.name, author.authorID, genre.name, genre.genreID\n" +
                        "from book \n" +
                        "inner join author\n" +
                        "on author.authorID = book.authorID\n" +
                        "inner join genre\n" +
                        "on genre.genreID = book.genreID", new BookMapper()
        );
    }

    @Override
    public Optional<Book> findByID(Long bookID) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bookID", bookID);
        Book res = namedParameterJdbcOperations.queryForObject(
                "select book.bookID, book.title, author.name, author.authorID, genre.name, genre.genreID\n" +
                        "from book \n" +
                        "inner join author on author.authorID = book.authorID\n" +
                        "inner join genre on genre.genreID = book.genreID\n"+
                        "where bookID = :bookID", params, new BookMapper()
        );
        return Optional.ofNullable(res);
    }

    @Override
    public List<Book> findBookByParam(Long authorID, Long genreID, String title) {
        String sql = String.format(
                "select book.bookID, book.title, author.name, author.authorID, genre.name, genre.genreID\n" +
                "from book \n" +
                "inner join author on author.authorID = book.authorID\n" +
                "inner join genre on genre.genreID = book.genreID\n"+
                "where book.title = '%s' and book.genreID = %d and book.authorID = %d",
                title, genreID, authorID);
        List<Book> res = namedParameterJdbcOperations.getJdbcOperations().query(sql, new BookMapper());
        return res;
    }

    @Override
    public boolean checkExists(Long bookID) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("bookID", bookID);
        int res =  namedParameterJdbcOperations.queryForObject(
                "select count(*) from book where bookID = :bookID", params, Integer.class
        );
        return res>0;
    }
}
