package com.gmail.alexflanker89.Lesson_8_MongoDB;

import com.gmail.alexflanker89.Lesson_8_MongoDB.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class Lesson8MongoDbApplication  implements ApplicationRunner {

	@Autowired
	private  BookRepo bookRepo;
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(Lesson8MongoDbApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

	}
}
