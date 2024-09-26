package com.gmail.alexflanker89.Lesson_8_MongoDB.services.interfaces;

import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();
    List<Genre> getAllGenreByBook(String id);
}
