package com.gmail.alexflanker89.lesson.services.interfaces;

import com.gmail.alexflanker89.lesson.domain.Author;
import com.gmail.alexflanker89.lesson.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();

    Author save(AuthorDTO authorDTO);

    Long count();
}
