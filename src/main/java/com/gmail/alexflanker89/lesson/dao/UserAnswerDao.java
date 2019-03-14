package com.gmail.alexflanker89.lesson.dao;

import com.gmail.alexflanker89.lesson.domain.Question;
import com.gmail.alexflanker89.lesson.domain.User;
import com.gmail.alexflanker89.lesson.domain.UserAnswer;

import java.util.List;

public interface UserAnswerDao {
    List<UserAnswer> findByUser(User user);
    List<UserAnswer> findByQuestion(Question question);
    void save(UserAnswer userAnswer);
}
