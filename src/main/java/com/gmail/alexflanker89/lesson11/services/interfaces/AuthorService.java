package com.gmail.alexflanker89.lesson11.services.interfaces;

import com.gmail.alexflanker89.lesson11.domain.Author;
import com.gmail.alexflanker89.lesson11.dto.AuthorDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthorService {
    Flux<Author> getAll();

    Mono<Author> save(Mono<AuthorDTO> authorDTO);
}
