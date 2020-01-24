package com.gmail.alexflanker89.lesson5.dao;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;

import java.time.LocalDate;
import java.util.List;

public interface AuthorRepository {
    Author findById(long id);
    List<Author> findAll();
    List<Author> findByName(String name);
    List<Author> findByLastname(String lastname);
    Author findByNameAndLastname(String name,String lastname);
    //    where x.ReleaseDate > ?
    List<Author> findByDateOfBirthGreaterThan(LocalDate dateTime);
    //    where x.ReleaseDate < ?
    List<Author> findByDateOfBirthLessThan(LocalDate dateTime);
    List<Author> findByDateOfBirth(LocalDate dateOfBirth);
    int deleteById(long id);
    Author save(Author author);
    Author update(Author author);
    List<Author> findAllByBook(Book book);
}
