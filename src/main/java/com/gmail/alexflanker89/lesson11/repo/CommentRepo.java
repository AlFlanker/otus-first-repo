package com.gmail.alexflanker89.lesson11.repo;

import com.gmail.alexflanker89.lesson11.domain.Comment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CommentRepo extends ReactiveMongoRepository<Comment, String> {
    Mono<Void> deleteById(String id);
}
