package com.gmail.alexflanker89.Lesson_10.repo;

import com.gmail.alexflanker89.Lesson_10.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Set;

public interface GenreRepo extends MongoRepository<Genre, String> {
    List<Genre> findByGenreNameIn(Set<String> names);
}
