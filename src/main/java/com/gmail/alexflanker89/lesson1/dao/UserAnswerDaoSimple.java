package com.gmail.alexflanker89.lesson1.dao;

import com.gmail.alexflanker89.lesson1.domain.Question;
import com.gmail.alexflanker89.lesson1.domain.User;
import com.gmail.alexflanker89.lesson1.domain.UserAnswer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserAnswerDaoSimple implements UserAnswerDao {
    private List<UserAnswer> userAnswers = new ArrayList<UserAnswer>();
    @Override
    public List<UserAnswer> findByUser(User user) {
        return userAnswers.stream().filter(ua -> ua.getUser().equals(user)).collect(Collectors.toList());
    }
    @Override
    public List<UserAnswer> findByQuestion(Question question) {
        return userAnswers.stream().filter(ua -> ua.getQuestion().equals(question)).collect(Collectors.toList());
    }

    @Override
    public void save(UserAnswer userAnswer) {
        userAnswers.add(userAnswer);
    }
}
