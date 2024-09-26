package com.gmail.alexflanker89.lesson11.services;

import com.gmail.alexflanker89.lesson11.domain.Author;
import com.gmail.alexflanker89.lesson11.dto.AuthorDTO;
import com.gmail.alexflanker89.lesson11.repo.AuthorRepo;
import com.gmail.alexflanker89.lesson11.services.interfaces.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthosServiceImpl implements AuthorService {
    private final AuthorRepo authorRepo;

    @Override
    public Flux<Author> getAll() {
        return authorRepo.findAll();
    }

    @Override
    public Mono<Author> save(Mono<AuthorDTO> authorDTO) {
        return authorDTO.map(dto->{
            Author author = new Author();
            author.setName(dto.getName());
            author.setLastname(dto.getLastname());
            author.setDateOfBirth(dto.getDateOfBirth());
            return authorRepo.save(author);
        }).flatMap(t->t);

    }
}
