package com.gmail.alexflanker89.lesson5.dao;

import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class GenreRepoJDBC implements GenreRepository {
    private final NamedParameterJdbcOperations jdbcOperations;
    private RowMapper<Genre> genreRowMapper = (ResultSet resultSet, int i) -> {
        Genre genre = new Genre();
        genre.setId(resultSet.getInt("id"));
        genre.setKindOf(resultSet.getString("kind_of"));
        genre.setIdDeleted(resultSet.getBoolean("is_deleted"));
        return genre;
    };
    public GenreRepoJDBC(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Genre findById(long id) {
        return null;
    }


    @Override
    public List<Genre> findAll() {
        return jdbcOperations.query("select * from genres", genreRowMapper);
    }

    @Override
    public int deleteById(long id) {
        return 0;
    }

    @Override
    public Genre save(Genre genre) {
        if(genre == null) throw new IllegalArgumentException();
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("kind_of",genre.getKindOf()).addValue("is_deleted",genre.isIdDeleted());
        return jdbcOperations.queryForObject("insert into genres (kind_of,is_deleted) values (:kind_of,:is_deleted)", parameterSource,genreRowMapper);
    }

    @Override
    public List<Genre> findAllByBook(Book book) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("book_id",book.getId());
        return jdbcOperations.query("select * from genres as g where g.id in (select brg.genre_id from book_ref_genre as brg where brg.book_id = :book_id)",param,genreRowMapper);
    }
}
