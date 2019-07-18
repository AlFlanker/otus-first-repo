package com.gmail.alexflanker89.lesson11.services.interfaces;

import com.gmail.alexflanker89.lesson11.domain.Book;
import com.gmail.alexflanker89.lesson11.dto.BookDTO;
import com.gmail.alexflanker89.lesson11.dto.criteria.RequestParams;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService  {

    Mono<Book> update(Book book);

    Mono<Book> save(Book entry);

    Flux<Book> getAll();

    Mono<Book> getBookById(String id);

    Mono<Book> addBook(Mono<BookDTO> bookDTO);

    Mono<Book> updateBook(Mono<Book> book, Mono<BookDTO> bookDTO);

    Flux<Book> getByParams(Mono<RequestParams> params);
}
