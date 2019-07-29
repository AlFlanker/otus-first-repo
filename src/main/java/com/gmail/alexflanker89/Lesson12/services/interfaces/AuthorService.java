package com.gmail.alexflanker89.Lesson12.services.interfaces;

import com.gmail.alexflanker89.Lesson12.domain.Author;
import com.gmail.alexflanker89.Lesson12.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();

    Author save(AuthorDTO authorDTO);
}
