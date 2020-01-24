package com.gmail.alexflanker89.lesson5.services;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BooksService {
    List<Book> getAll();
    List<Book> getAllByGenre(Genre genre);
    List<Book> getAllByAuthor(Author authors);
    Book getDetailInfoByID(long id);
    List<Book> getAllByReleaseDateGreaterThan(LocalDate date);
    List<Book> getAllByReleaseDateLessThan(LocalDate date);
    List<Book> getAllByReleaseDate(LocalDate date);
    Book saveBook(Book book);
    void updateBook(Book book);
    int deleteBook(Book book);

}
