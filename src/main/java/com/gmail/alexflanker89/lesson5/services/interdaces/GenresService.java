package com.gmail.alexflanker89.lesson5.services.interdaces;

import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;

import java.util.List;

public interface GenresService extends BaseService<Genre>{
    List<Genre> getAllByBook(Book book);
    Genre getByGenreName(String name);
}
