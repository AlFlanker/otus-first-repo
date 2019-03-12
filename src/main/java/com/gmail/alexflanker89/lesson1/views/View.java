package com.gmail.alexflanker89.lesson1.views;


import com.gmail.alexflanker89.lesson1.domain.Question;

public interface View {
    void showMessage(String message);
    String ask();
    void showQuestion(Question question);


}
