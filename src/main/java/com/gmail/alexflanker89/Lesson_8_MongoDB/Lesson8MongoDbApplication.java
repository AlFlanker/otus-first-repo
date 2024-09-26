package com.gmail.alexflanker89.Lesson_8_MongoDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class Lesson8MongoDbApplication{
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(Lesson8MongoDbApplication.class, args);
	}
}
