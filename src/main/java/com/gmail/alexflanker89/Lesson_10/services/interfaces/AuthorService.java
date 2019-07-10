package com.gmail.alexflanker89.Lesson_10.services.interfaces;

import com.gmail.alexflanker89.Lesson_10.domain.Author;
import com.gmail.alexflanker89.Lesson_10.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();

    Author save(AuthorDTO authorDTO);
}
