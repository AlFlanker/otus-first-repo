package com.gmail.alexflanker89.lesson11.repo;

import com.gmail.alexflanker89.lesson11.domain.Author;
import com.gmail.alexflanker89.lesson11.domain.Book;
import com.gmail.alexflanker89.lesson11.domain.Genre;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Set;

public interface BookRepo extends ReactiveMongoRepository<Book, String> {
    Flux<Book> findByReleaseDate(LocalDate date);

    Mono<Book> findByTitleAndEditionAndReleaseDate(String title, String edition, LocalDate date);

    Flux<Book> findByReleaseDateBefore(LocalDate date);

    Flux<Book> findByReleaseDateGreaterThan(LocalDate date);

    Flux<Book> findByAuthorsIn(Set<Author> authors);

    Flux<Book> findByGenresIn(Set<Genre> genres);

    Mono<Book> findById(String id);

    Mono<Book> findByTitle(String title);

    Mono<Book> findById(Mono<String> id);


}

