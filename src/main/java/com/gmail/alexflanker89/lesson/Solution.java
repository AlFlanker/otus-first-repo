package com.gmail.alexflanker89.lesson;

import com.gmail.alexflanker89.lesson.controller.TestController;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
@ComponentScan
@RequiredArgsConstructor
public class Solution {
    private final TestController controller;
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Solution.class);
        context.getBean(Solution.class).controller.startTest();
    }





}
