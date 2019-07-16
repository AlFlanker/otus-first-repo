package com.gmail.alexflanker89.lesson11.repo;

import com.gmail.alexflanker89.lesson11.domain.Author;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.Set;

public interface AuthorRepo extends ReactiveMongoRepository<Author, String> {

    Flux<Author> findAll();

    Flux<Author> findByNameAndLastname(String name, String lastname);

    Flux<Author> findByNameAndLastnameAndDateOfBirth(String name, String lastname, LocalDate date);

    Flux<Author> findByIdIn(Set<String> ids);

}
