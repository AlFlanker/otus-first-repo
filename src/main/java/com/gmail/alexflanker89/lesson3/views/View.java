package com.gmail.alexflanker89.lesson3.views;

import com.gmail.alexflanker89.lesson3.domain.Question;

public interface View {
    void showMessage(String message);
    String ask();
    void showQuestion(Question question);



}
