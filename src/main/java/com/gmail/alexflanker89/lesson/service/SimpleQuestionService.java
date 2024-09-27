package com.gmail.alexflanker89.lesson.service;

import com.gmail.alexflanker89.lesson.dao.QuestionDao;
import com.gmail.alexflanker89.lesson.domain.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
@Service
public class SimpleQuestionService implements QuestionService {
    private QuestionDao questionDao;

    public SimpleQuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> getQuestions() {
        return questionDao.findAll();
    }

    @Override
    public Question getQuestion(long id) {
        return questionDao.findById(id);
    }

    @Override
    public void setPath(Path path) {
        questionDao.setPath(path);
    }
}
