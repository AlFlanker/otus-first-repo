package com.gmail.alexflanker89.lesson1;

import com.gmail.alexflanker89.lesson1.controller.TestController;
import com.gmail.alexflanker89.lesson1.controller.TestControllerImpl;
import com.gmail.alexflanker89.lesson1.domain.Question;
import com.gmail.alexflanker89.lesson1.domain.User;
import com.gmail.alexflanker89.lesson1.domain.UserAnswer;
import com.gmail.alexflanker89.lesson1.service.QuestionService;
import com.gmail.alexflanker89.lesson1.service.UserAnswerService;
import com.gmail.alexflanker89.lesson1.service.UserService;
import com.gmail.alexflanker89.lesson1.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.gmail.alexflanker89.lesson1.views.View;

import java.util.List;

//1.Так - у Вас нет как такового пакейджа - Вам необходимо создать что-то вроде ru.otus или com.gmail.alexflanker89.
//// Готово
@RequiredArgsConstructor
public class Solution {
    private final TestController controller;
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/lesson1/spring-context.xml");
        context.getBean(Solution.class).controller.startTest();

    }





}
