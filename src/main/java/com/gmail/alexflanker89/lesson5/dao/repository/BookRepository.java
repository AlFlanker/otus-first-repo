package com.gmail.alexflanker89.lesson5.dao.repository;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByGenresIn(Set<Genre> genres);
    Set<Book> findByAuthorsIn(Set<Author> authors);
    Set<Book> findAllByReleaseDateGreaterThan(LocalDate date);
    Set<Book> findAllByReleaseDateLessThan(LocalDate date);
    Set<Book> findAllByReleaseDate(LocalDate date);

}
