package com.gmail.alexflanker89.lesson11.services.interfaces;

import com.gmail.alexflanker89.lesson11.domain.Book;
import com.gmail.alexflanker89.lesson11.domain.Comment;
import com.gmail.alexflanker89.lesson11.dto.CommentDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentService {
    Mono<Book> addComment(Mono<Book> book, Mono<CommentDTO> dto);

    Mono<Book> removeComment(String id,Mono<String> comment);

    Flux<Comment> getAllCommentByBookId(String bookid);
}
