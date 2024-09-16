package com.gmail.alexflanker89.Lesson_10.services.interfaces;

import com.gmail.alexflanker89.Lesson_10.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();

    List<Genre> getAllGenreByBook(String id);
}
