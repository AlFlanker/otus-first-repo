package com.gmail.alexflanker89.lesson1.service;

import com.gmail.alexflanker89.lesson1.dao.QuestionDao;
import com.gmail.alexflanker89.lesson1.domain.Question;

import java.util.List;

public class SimpleQuestionService implements QuestionService {
    private QuestionDao questionDao;

    public SimpleQuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public List<Question> getQuestions() {
        return questionDao.findAll();
    }

    public Question getQuestion(long id) {
        return questionDao.findById(id);
    }
}
