package com.gmail.alexflanker89.lesson4.dao;

import com.gmail.alexflanker89.lesson4.domain.Question;
import com.gmail.alexflanker89.lesson4.domain.User;
import com.gmail.alexflanker89.lesson4.domain.UserAnswer;

import java.util.List;

public interface UserAnswerDao {
    List<UserAnswer> findByUser(User user);
    List<UserAnswer> findByQuestion(Question question);
    void save(UserAnswer userAnswer);
}
