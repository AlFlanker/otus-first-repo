package com.gmail.alexflanker89.lesson4.service;

import com.gmail.alexflanker89.lesson4.domain.User;
import com.gmail.alexflanker89.lesson4.domain.UserAnswer;

import java.util.List;

public interface UserAnswerService {
    void reqAnswer(UserAnswer answer);
    List<UserAnswer> getAllByUser(User user);
}
