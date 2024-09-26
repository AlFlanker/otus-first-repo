package com.gmail.alexflanker89.lesson11.handler;

import com.gmail.alexflanker89.lesson11.domain.Book;
import com.gmail.alexflanker89.lesson11.dto.BookDTO;
import com.gmail.alexflanker89.lesson11.dto.criteria.RequestParams;
import com.gmail.alexflanker89.lesson11.services.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class BookHandler {
    private final BookService bookService;

    public Mono<ServerResponse> getAllBook(ServerRequest request) {
        return request.queryParam("id").map(id -> ServerResponse.ok().body(bookService.getBookById(id), Book.class))
                .orElse(ServerResponse.ok().body(bookService.getAll(), Book.class));
    }

    public Mono<ServerResponse> getByParams(ServerRequest request) {
        return ServerResponse.status(HttpStatus.OK).body(bookService.getByParams(request.bodyToMono(RequestParams.class)),Book.class);
    }

    public Mono<ServerResponse> addBook(ServerRequest request) {
        return ServerResponse.status(HttpStatus.CREATED).body(bookService.addBook(request.bodyToMono(BookDTO.class)),Book.class);
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        return ServerResponse.ok()
                .body(bookService.updateBook(
                        bookService.getBookById(request.pathVariable("id"))
                        ,request.bodyToMono(BookDTO.class))
                        ,Book.class);

    }
}
