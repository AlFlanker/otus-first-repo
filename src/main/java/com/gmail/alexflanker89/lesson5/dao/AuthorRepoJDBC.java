package com.gmail.alexflanker89.lesson5.dao;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

@Repository
public class AuthorRepoJDBC implements AuthorRepository {
    private final NamedParameterJdbcOperations jdbcOperations;

    private RowMapper<Author> authorRowMapper = (ResultSet resultSet, int i) -> {
        Author author = new Author();
        author.setId(resultSet.getLong("id"));
        author.setName(resultSet.getString("name"));
        author.setLastname(resultSet.getString("lastname"));
        author.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
        author.setIdDeleted(resultSet.getBoolean("is_deleted"));
        return author;
    };

    public AuthorRepoJDBC(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Author findById(long id) {
        SqlParameterSource parameter = new MapSqlParameterSource().addValue("id", id);
        return jdbcOperations.queryForObject("select * from authors where id = :id", parameter, authorRowMapper);
    }

    @Override
    public List<Author> findAll() {
        return jdbcOperations.query("select * from authors", authorRowMapper);
    }

    @Override
    public List<Author> findByName(String name) {
        SqlParameterSource parameter = new MapSqlParameterSource().addValue("name", name);
        return jdbcOperations.query("select * from authors where name = :name", parameter, authorRowMapper);
    }

    @Override
    public List<Author> findByLastname(String lastname) {
        SqlParameterSource parameter = new MapSqlParameterSource().addValue("lastname", lastname);
        return jdbcOperations.query("select * from authors where lastname = :lastname", parameter, authorRowMapper);
    }

    @Override
    public Author findByNameAndLastname(String name, String lastname) {
        SqlParameterSource parameter = new MapSqlParameterSource().addValue("name", name).addValue("lastname",lastname);
        return jdbcOperations.queryForObject("select * from authors where name = :name and lastname= :lastname", parameter, authorRowMapper);
    }

    @Override
    public List<Author> findByDateOfBirthGreaterThan(LocalDate dateTime) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("dateOfBirth", dateTime);
        return jdbcOperations.query("select * from authors where date_of_birth > :dateOfBirth", sqlParameterSource, authorRowMapper);
    }

    @Override
    public List<Author> findByDateOfBirthLessThan(LocalDate dateTime) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("dateOfBirth", dateTime);
        return jdbcOperations.query("select * from authors where date_of_birth < :dateOfBirth", sqlParameterSource, authorRowMapper);
    }

    @Override
    public List<Author> findByDateOfBirth(LocalDate dateOfBirth) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("dateOfBirth", dateOfBirth);
        return jdbcOperations.query("select * from authors where date_of_birth = :dateOfBirth", sqlParameterSource, authorRowMapper);
    }

    @Override
    public int deleteById(long id) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        return jdbcOperations.getJdbcOperations().update("delete from authors where id = :id", param);
    }

    @Override
    public Author save(Author author) {
        if(author == null) throw new IllegalArgumentException();
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("name",author.getName()).addValue("lastname",author.getLastname())
                .addValue("date_of_birth",author.getDateOfBirth()).addValue("is_deleted",author.isIdDeleted());
        return jdbcOperations.queryForObject("insert into authors (name,lastname,date_of_birth,is_deleted) values (:name,:lastname,:date_of_birth,:is_deleted)", parameterSource,authorRowMapper);
    }

    @Override
    public Author update(Author author) {
        return null;
    }

    @Override
    public List<Author> findAllByBook(Book book) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("book_id",book.getId());
        return jdbcOperations.query("select * from authors as a where a.id in (select bra.author_id from book_ref_author as bra where bra.book_id = :book_id)",params,authorRowMapper);
    }
}
