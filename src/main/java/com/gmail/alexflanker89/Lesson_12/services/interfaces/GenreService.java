package com.gmail.alexflanker89.Lesson_12.services.interfaces;

import com.gmail.alexflanker89.Lesson_12.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();

    List<Genre> getAllGenreByBook(String id);
}
