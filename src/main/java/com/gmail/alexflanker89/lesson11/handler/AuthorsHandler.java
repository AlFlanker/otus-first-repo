package com.gmail.alexflanker89.lesson11.handler;


import com.gmail.alexflanker89.lesson11.domain.Author;
import com.gmail.alexflanker89.lesson11.dto.AuthorDTO;
import com.gmail.alexflanker89.lesson11.services.interfaces.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorsHandler {
    private final AuthorService authorService;


    public Mono<ServerResponse> getAuthors(ServerRequest request) {
        return ServerResponse.ok().body(authorService.getAll(),Author.class);
    }


    public Mono<ServerResponse> addAuthor(ServerRequest request) {
        return ServerResponse.status(HttpStatus.CREATED).body(authorService.save(request.bodyToMono(AuthorDTO.class)), Author.class);
    }
}
















