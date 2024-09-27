package com.gmail.alexflanker89.lesson.repo;

import com.gmail.alexflanker89.lesson.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface AuthorRepo extends MongoRepository<Author, String> {
    List<Author> findByNameAndLastname(String name, String lastname);

    List<Author> findByNameAndLastnameAndDateOfBirth(String name, String lastname, LocalDate date);

    List<Author> findByIdIn(Set<String> ids);

}
