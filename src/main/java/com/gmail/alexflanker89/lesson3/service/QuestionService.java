package com.gmail.alexflanker89.lesson3.service;

import com.gmail.alexflanker89.lesson3.domain.Question;
import java.nio.file.Path;
import java.util.List;

public interface QuestionService {
    List<Question> getQuestions();
    Question getQuestion(long id);
    void setPath(Path path);
}
