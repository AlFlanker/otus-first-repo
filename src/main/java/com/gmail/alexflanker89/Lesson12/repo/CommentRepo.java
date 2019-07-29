package com.gmail.alexflanker89.Lesson12.repo;

import com.gmail.alexflanker89.Lesson12.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepo extends MongoRepository<Comment, String> {

}
