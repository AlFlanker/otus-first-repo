package com.gmail.alexflanker89.Lesson_8_MongoDB.services.interfaces;

import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getByNameAndLastname(String name, String lastname);
    List<Author> getAllAuthor();
}
