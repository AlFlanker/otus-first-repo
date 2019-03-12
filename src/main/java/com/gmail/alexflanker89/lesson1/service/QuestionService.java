package com.gmail.alexflanker89.lesson1.service;

import com.gmail.alexflanker89.lesson1.domain.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestions();
    Question getQuestion(long id);
}
