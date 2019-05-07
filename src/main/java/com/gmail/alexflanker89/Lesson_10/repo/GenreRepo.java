package com.gmail.alexflanker89.Lesson_10.repo;

import com.gmail.alexflanker89.Lesson_10.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepo extends MongoRepository<Genre,String> {
}
