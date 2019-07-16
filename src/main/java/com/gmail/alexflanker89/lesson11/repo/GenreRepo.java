package com.gmail.alexflanker89.lesson11.repo;

import com.gmail.alexflanker89.lesson11.domain.Genre;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Set;

public interface GenreRepo extends ReactiveMongoRepository<Genre, String> {
    Flux<Genre> findByGenreNameIn(Set<String> names);
}
