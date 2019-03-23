package com.gmail.alexflanker89.lesson4.controller;

import com.gmail.alexflanker89.lesson4.domain.User;

import java.util.Locale;

public interface TestController {
    void showResult(User user);
    void setLocale(Locale locale);
    Locale getLocale();

}
