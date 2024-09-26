package com.gmail.alexflanker89.lesson11.services;

import com.gmail.alexflanker89.lesson11.domain.Book;
import com.gmail.alexflanker89.lesson11.domain.Comment;
import com.gmail.alexflanker89.lesson11.dto.CommentDTO;
import com.gmail.alexflanker89.lesson11.repo.BookRepo;
import com.gmail.alexflanker89.lesson11.repo.CommentRepo;
import com.gmail.alexflanker89.lesson11.services.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final BookRepo bookRepo;
    private final CommentRepo commentRepo;


    private Comment convertFromDTO(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setId(null);
        comment.setComment(dto.getComment());
        comment.setCreated(LocalDateTime.now());
        comment.setUsername(dto.getUsername());
        return comment;
    }

    @Override
    public Mono<Book> addComment(Mono<Book> book, Mono<CommentDTO> commentDto) {
        return commentDto.map(this::convertFromDTO).map(c ->
                book.map(b -> {
                    b.getComments().add(c);
                    return bookRepo.save(b);
                })).flatMap(f -> f).flatMap(y -> y);

    }

    @Override
    public Mono<Book> removeComment(String book, Mono<String> comment){
        return bookRepo.findById(book).map(b->
            commentRepo.findById(comment).map(c-> {
                b.getComments().remove(c);
                return bookRepo.save(b);
            })
        ).flatMap(t->t).flatMap(y->y);
    }

}
