package com.gmail.alexflanker89.lesson1.controller;

import com.gmail.alexflanker89.lesson1.domain.User;

public interface TestController {
    void startTest();
    void startAskTheQuestions(User user);
    void showResult(User user);

}
