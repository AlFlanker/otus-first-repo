package com.gmail.alexflanker89.lesson4.dao;

import com.gmail.alexflanker89.lesson4.domain.Question;

import java.nio.file.Path;
import java.util.List;

public interface QuestionDao {
    Question findById(long id);
    List<Question> findAll();
    void setPath(Path path);
}
