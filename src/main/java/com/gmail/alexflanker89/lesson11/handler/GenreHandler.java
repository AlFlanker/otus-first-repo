package com.gmail.alexflanker89.lesson11.handler;

import com.gmail.alexflanker89.lesson11.domain.Genre;
import com.gmail.alexflanker89.lesson11.services.interfaces.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GenreHandler {
    private final GenreService genreService;

    public Mono<ServerResponse> getAllGenres(ServerRequest serverRequest){
        return ServerResponse.ok().body(genreService.getAll(), Genre.class);
    }


}
