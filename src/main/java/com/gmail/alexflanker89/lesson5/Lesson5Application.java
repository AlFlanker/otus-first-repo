package com.gmail.alexflanker89.lesson5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lesson5Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Lesson5Application.class);
        springApplication.run(args);
    }

}
