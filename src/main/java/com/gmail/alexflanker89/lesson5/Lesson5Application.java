package com.gmail.alexflanker89.lesson5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Lesson5Application {


    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Lesson5Application.class);
        ConfigurableApplicationContext context = springApplication.run(args);

    }


}
