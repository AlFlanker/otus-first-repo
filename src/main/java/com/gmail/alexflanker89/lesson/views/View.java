package com.gmail.alexflanker89.lesson.views;


import com.gmail.alexflanker89.lesson.domain.Question;
import com.gmail.alexflanker89.lesson.domain.UserAnswer;

import java.util.Locale;

public interface View {
    void showMessage(String message);
    String ask();
    void showQuestion(Question question);



}
