package com.gmail.alexflanker89.lesson11.handler;

import com.gmail.alexflanker89.lesson11.domain.Book;
import com.gmail.alexflanker89.lesson11.dto.CommentDTO;
import com.gmail.alexflanker89.lesson11.services.interfaces.BookService;
import com.gmail.alexflanker89.lesson11.services.interfaces.CommentService;
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
public class CommentHandler {
    private final CommentService commentService;
    private final BookService bookService;

    public Mono<ServerResponse> addComment(ServerRequest request) {
            return ServerResponse.status(HttpStatus.CREATED)
                    .body(commentService
                            .addComment(bookService.getBookById(request.pathVariable("id")),
                                    request.bodyToMono(CommentDTO.class)), Book.class);

    }

    public Mono<ServerResponse> removeComment(ServerRequest request) {
            return ServerResponse.ok().body(commentService.removeComment(request.pathVariable("id"),request.bodyToMono(String.class)),Book.class);
    }
}
