package com.gmail.alexflanker89.lesson5.services;

import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;

import java.util.List;

public interface GenresService {
    List<Genre> getAll();
    List<Genre> getAllByBook(Book book);
}
