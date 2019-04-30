package com.gmail.alexflanker89.Lesson_8_MongoDB.repo;

import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepo extends MongoRepository<Genre,String> {
}
