package com.gmail.alexflanker89.Lesson_8_MongoDB.repo;

import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuthorRepo extends MongoRepository<Author,String> {
    List<Author> findByNameAndLastname(String name, String lastname);

}
