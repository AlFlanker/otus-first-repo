package com.gmail.alexflanker89.Lesson_10.repo;

import com.gmail.alexflanker89.Lesson_10.domain.Author;
import com.gmail.alexflanker89.Lesson_10.domain.Book;
import com.gmail.alexflanker89.Lesson_10.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookRepo extends MongoRepository<Book, String> {
    List<Book> findByReleaseDate(LocalDate date);

    Book findByTitleAndEditionAndReleaseDate(String title, String edition, LocalDate date);

    List<Book> findByReleaseDateBefore(LocalDate date);

    List<Book> findByReleaseDateGreaterThan(LocalDate date);

    List<Book> findByAuthorsIn(Set<Author> authors);

    List<Book> findByGenresIn(Set<Genre> genres);

    Optional<Book> findById(String id);

    Optional<Book> findByTitle(String title);

}

