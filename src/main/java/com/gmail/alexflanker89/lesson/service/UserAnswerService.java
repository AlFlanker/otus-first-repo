package com.gmail.alexflanker89.lesson.service;

import com.gmail.alexflanker89.lesson.domain.User;
import com.gmail.alexflanker89.lesson.domain.UserAnswer;

import java.util.List;

public interface UserAnswerService {
    void reqAnswer(UserAnswer answer);
    List<UserAnswer> getAllByUser(User user);
}
