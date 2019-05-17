package com.gmail.alexflanker89.Lesson_10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class LibraryApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(LibraryApplication.class, args);
	}
}
