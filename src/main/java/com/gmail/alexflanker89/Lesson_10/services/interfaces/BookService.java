package com.gmail.alexflanker89.Lesson_10.services.interfaces;

import com.gmail.alexflanker89.Lesson_10.domain.Author;
import com.gmail.alexflanker89.Lesson_10.domain.Book;
import com.gmail.alexflanker89.Lesson_10.dto.BookDTO;
import com.gmail.alexflanker89.Lesson_10.dto.criteria.RequestParams;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookService extends BaseService<Book> {
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
}
