package com.gmail.alexflanker89.lesson5.dao;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository {
    Book findById(long id);
    List<Book> findAll();
    List<Book> findByGenre(Genre genre);
    //    where x.ReleaseDate > ?
    List<Book> findByReleaseDateGreaterThan(LocalDate dateTime);
    //    where x.ReleaseDate < ?
    List<Book> findByReleaseDateLessThan(LocalDate dateTime);
    List<Book> findByAuthor(Author author);
    List<Book> findByReleaseDate(LocalDate releaseDate);
    int deleteById(long id);
    Book save(Book book);
    void update(Book book);


}
