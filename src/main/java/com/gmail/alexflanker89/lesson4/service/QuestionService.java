package com.gmail.alexflanker89.lesson4.service;

import com.gmail.alexflanker89.lesson4.domain.Question;

import java.nio.file.Path;
import java.util.List;

public interface QuestionService {
    List<Question> getQuestions();
    Question getQuestion(long id);
    void setPath(Path path);
}
