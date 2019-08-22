package com.gmail.alexflanker89.lesson3;

import com.gmail.alexflanker89.lesson3.controller.TestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Lesson3Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Lesson3Application.class, args);
        context.getBean(TestController.class).startTest();
    }

}
