package com.gmail.alexflanker89.lesson11.services.interfaces;

import com.gmail.alexflanker89.lesson11.domain.Author;
import com.gmail.alexflanker89.lesson11.domain.Book;
import com.gmail.alexflanker89.lesson11.dto.BookDTO;
import com.gmail.alexflanker89.lesson11.dto.criteria.RequestParams;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Set;

public interface BookService  {
    Flux<Book> getAllByGenres(String genres);

    Flux<Book> getAllByGenres(String[] genres);

    Flux<Book> getAllByAuthors(Set<Author> authors);

    Flux<Book> getAllByReleaseDateGreaterThan(LocalDate date);

    Flux<Book> getAllByReleaseDateLessThan(LocalDate date);

    Flux<Book> getAllByReleaseDate(LocalDate date);

    Flux<Author> getByNameAndLastname(String name, String lastname);

    Flux<Book> getAllByAuthorsNameAndLastname(String name, String lastname);

    Flux<Author> getAllAuthor();

    void delete(Book entry);

    Mono<Book> update(Book book);

    Mono<Book> save(Book entry);

    Flux<Book> getAll();

    Mono<Book> getBookById(String id);

    Mono<Book> getByTitle(String title);

    Mono<Book> addBook(Mono<BookDTO> bookDTO);

    Mono<Book> updateBook(Mono<Book> book, Mono<BookDTO> bookDTO);

    Flux<Book> getByParams(Mono<RequestParams> params);
}
