package com.gmail.alexflanker89.lesson.repo;

import com.gmail.alexflanker89.lesson.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepo extends MongoRepository<Comment, String> {

}
