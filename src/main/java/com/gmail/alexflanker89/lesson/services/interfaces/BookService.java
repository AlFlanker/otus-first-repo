package com.gmail.alexflanker89.lesson.services.interfaces;

import com.gmail.alexflanker89.lesson.domain.Author;
import com.gmail.alexflanker89.lesson.domain.Book;
import com.gmail.alexflanker89.lesson.dto.BookDTO;
import com.gmail.alexflanker89.lesson.dto.criteria.RequestParams;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookService{
    List<Book> getAllByGenres(String genres);

    List<Book> getAllByGenres(String[] genres);

    List<Book> getAllByAuthors(Set<Author> authors);

    List<Book> getAllByReleaseDateGreaterThan(LocalDate date);

    List<Book> getAllByReleaseDateLessThan(LocalDate date);

    List<Book> getAllByReleaseDate(LocalDate date);

    List<Author> getByNameAndLastname(String name, String lastname);

    List<Book> getAllByAuthorsNameAndLastname(String name, String lastname);

    List<Author> getAllAuthor();

    List<Author> getByBookId(String id);

    Optional<Book> getBookById(String id);

    Optional<Book> getByTitle(String title);

    Book addBook(BookDTO bookDTO);

    Book updateBook(Book book, BookDTO bookDTO);

    List<Book> getByParams(RequestParams params);

    List<Book> getAll();

    void delete(Book entry);

    Book save(Book entry);

    Book update(Book entry);

    long count();
}
