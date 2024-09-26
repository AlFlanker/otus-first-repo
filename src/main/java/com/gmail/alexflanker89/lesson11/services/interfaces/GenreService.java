package com.gmail.alexflanker89.lesson11.services.interfaces;

import com.gmail.alexflanker89.lesson11.domain.Genre;
import reactor.core.publisher.Flux;

public interface GenreService {
    Flux<Genre> getAll();

}
