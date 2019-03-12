package com.gmail.alexflanker89.lesson1.dao;

import com.gmail.alexflanker89.lesson1.domain.Question;

import java.util.List;

public interface QuestionDao {
    Question findById(long id);
    List<Question> findAll();
}
