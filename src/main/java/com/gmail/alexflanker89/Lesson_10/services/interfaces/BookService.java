package com.gmail.alexflanker89.Lesson_10.services.interfaces;

import com.gmail.alexflanker89.Lesson_10.domain.Author;
import com.gmail.alexflanker89.Lesson_10.domain.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookService extends BaseService<Book>{
    List<Book> getAllByGenres(String genres);
    List<Book> getAllByAuthors(Set<Author> authors);
    List<Book> getAllByReleaseDateGreaterThan(LocalDate date);
    List<Book> getAllByReleaseDateLessThan(LocalDate date);
    List<Book> getAllByReleaseDate(LocalDate date);
    List<Author> getByNameAndLastname(java.lang.String name, java.lang.String lastname);
    List<Book> getAllByAuthorsNameAndLastname(java.lang.String name, java.lang.String lastname);
    List<Author> getAllAuthor();
    List<Author> getByBookId(java.lang.String id);
    Optional<Book> getBookById(String id);
}
