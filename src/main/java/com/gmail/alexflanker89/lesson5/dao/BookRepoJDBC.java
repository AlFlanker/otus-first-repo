package com.gmail.alexflanker89.lesson5.dao;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Repository
public class BookRepoJDBC implements BookRepository {
    private final NamedParameterJdbcOperations jdbcOperations;

    private RowMapper<Book> bookRowMapper = (ResultSet resultSet, int i) -> {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setTitle(resultSet.getString("title"));
        book.setEdition(resultSet.getString("edition"));
        book.setDescription(resultSet.getString("description"));
        book.setLanguages(Collections.emptySet());
        book.setReleaseDate(resultSet.getDate("releasedate").toLocalDate());
        book.setGenres(Collections.emptySet());
        book.setAuthors(Collections.emptySet());
        book.setIdDeleted(resultSet.getBoolean("is_deleted"));
        return book;
    };

    public BookRepoJDBC(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;

    }

    @Override
    public Book findById(long id) throws EmptyResultDataAccessException {
        SqlParameterSource parameter = new MapSqlParameterSource().addValue("id", id);
        return jdbcOperations.queryForObject("select * from books where id = :id", parameter, bookRowMapper);

    }

    @Override
    public List<Book> findAll() {
        return jdbcOperations.query("select * from books", bookRowMapper);
    }

    @Override
    public List<Book> findByReleaseDateGreaterThan(LocalDate dateTime) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("releaseDate", dateTime);
        return jdbcOperations.query("select * from books where releasedate > :releaseDate", sqlParameterSource, bookRowMapper);
    }

    @Override
    public List<Book> findByReleaseDateLessThan(LocalDate dateTime) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("releaseDate", dateTime);
        return jdbcOperations.query("select * from books where releasedate < :releaseDate", sqlParameterSource, bookRowMapper);
    }

    @Override
    public List<Book> findByAuthor(Author author) throws IllegalArgumentException{
        if(author == null) throw new IllegalArgumentException();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("name", author.getName()).addValue("lastname", author.getLastname());
        final String authorID = "(select a.id from authors as a where a.name = :name and a.lastname = :lastname)";
        final String bookID = "(select book_id from book_ref_author as bra where bra.author_id IN " + authorID + ")";
        final String books = "select * from books where id IN " + bookID;
        return jdbcOperations.query(books, sqlParameterSource, bookRowMapper);
    }

    @Override
    public List<Book> findByReleaseDate(LocalDate releaseDate) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("releaseDate", releaseDate);
        return jdbcOperations.query("select * from books where releasedate = :releaseDate", sqlParameterSource, bookRowMapper);
    }

    @Override
    public int deleteById(long id) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        return jdbcOperations.getJdbcOperations().update("delete from books where id = :id", param);
    }

    @Override
    public List<Book> findByGenre(Genre genre) throws IllegalArgumentException {
        if(genre == null) throw new IllegalArgumentException();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("kindOf", genre.getKindOf());
        final String bookID = "(select book_id from book_ref_genre as brg where brg.genre_id = (select genres.id from genres where genres.kind_Of=:kindOf))";
        final String books = "select * from books where id IN " + bookID;
        return jdbcOperations.query(books, sqlParameterSource, bookRowMapper);
    }

    @Override
    public Book save(Book book) throws IllegalArgumentException{
        final String insert = "insert into books (title,edition,description,releaseDate,is_deleted) values (:title,:edition,:description,:releaseDate,:idDeleted)";
        if(book == null) throw new IllegalArgumentException();
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("title",book.getTitle()).addValue("edition",book.getEdition()).addValue("description",book.getDescription())
                .addValue("releaseDate",book.getReleaseDate()).addValue("idDeleted",book.isIdDeleted());
        return jdbcOperations.queryForObject("insert into books (title,edition,description,releaseDate,is_deleted) values (:title,:edition,:description,:releaseDate,:idDeleted)", parameterSource,bookRowMapper);

    }

    @Override
    public void update(Book book) throws IllegalArgumentException {
        if(book == null) throw new IllegalArgumentException();
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("title",book.getTitle()).addValue("edition",book.getEdition()).addValue("description",book.getDescription())
                .addValue("releaseDate",book.getReleaseDate()).addValue("idDeleted",book.isIdDeleted()).addValue("id",book.getId());
         jdbcOperations.queryForObject("update books set title = :title, edition=:edition, description=:description, releaseDate=:releaseDate, is_deleted=:idDeleted where id = :id", parameterSource,bookRowMapper);

    }
}
