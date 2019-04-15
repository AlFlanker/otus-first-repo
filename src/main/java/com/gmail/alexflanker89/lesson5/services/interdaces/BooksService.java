package com.gmail.alexflanker89.lesson5.services.interdaces;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BooksService extends BaseService<Book>{
    List<Book> getAllByGenres(Set<Genre> genres);
    Set<Book> getAllByAuthors(Set<Author> authors);
    Set<Book> getAllByReleaseDateGreaterThan(LocalDate date);
    Set<Book> getAllByReleaseDateLessThan(LocalDate date);
    Set<Book> getAllByReleaseDate(LocalDate date);

}
