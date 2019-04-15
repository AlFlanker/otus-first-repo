package com.gmail.alexflanker89.lesson5.dao.interfaces;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookRepository extends BaseRepository<Book> {
    List<Book> findByGernes(Set<Genre> genres);
    Set<Book> findByAuthors(Set<Author> authors);
    Set<Book> findByTitle(String title);
    Set<Book> findAllByReleaseDateGreaterThan(LocalDate date);
    Set<Book> findAllByReleaseDateLessThan(LocalDate date);
    Set<Book> findAllByReleaseDate(LocalDate date);

}
