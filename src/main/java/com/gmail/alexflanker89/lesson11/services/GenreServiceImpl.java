package com.gmail.alexflanker89.lesson11.services;

import com.gmail.alexflanker89.lesson11.domain.Genre;
import com.gmail.alexflanker89.lesson11.repo.GenreRepo;
import com.gmail.alexflanker89.lesson11.services.interfaces.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepo genreRepo;

    @Override
    public Flux<Genre> getAll() {
        return genreRepo.findAll();
    }

}
