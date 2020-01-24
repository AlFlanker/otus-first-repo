package com.gmail.alexflanker89.lesson5.dao;

import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;

import java.util.List;

public interface GenreRepository {
    Genre findById(long id);
    List<Genre> findAll();
    int deleteById(long id);
    Genre save(Genre genre);
    List<Genre> findAllByBook(Book book);
}
