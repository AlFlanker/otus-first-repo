package com.gmail.alexflanker89.lesson.services.interfaces;

import com.gmail.alexflanker89.lesson.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();

    List<Genre> getAllGenreByBook(String id);
}
