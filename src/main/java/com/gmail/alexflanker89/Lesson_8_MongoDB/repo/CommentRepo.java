package com.gmail.alexflanker89.Lesson_8_MongoDB.repo;

import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepo  extends MongoRepository<Comment,String> {

}
