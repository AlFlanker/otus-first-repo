package com.gmail.alexflanker89.lesson5.services;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;

import java.time.LocalDate;
import java.util.List;

public interface AuthorsService {
    List<Author> getAll();
    Author getById(long id);
    List<Author>  getByName(String name);
    List<Author>  getByLastname(String lastname);
    Author getByNameAndLastname(String name,String lastname);
    List<Author> getAllByDateOfBirthGreaterThan(LocalDate date);
    List<Author> getAllByDateOfBirthLessThan(LocalDate date);
    List<Author> getAllByDateOfBirth(LocalDate date);
    List<Author> getAllByBook(Book book);

}
