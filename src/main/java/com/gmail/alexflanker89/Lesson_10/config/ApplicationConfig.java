package com.gmail.alexflanker89.Lesson_10.config;


import com.github.cloudyrock.mongock.SpringBootMongock;
import com.github.cloudyrock.mongock.SpringBootMongockBuilder;
import com.mongodb.MongoClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfig {

    private static final String CHANGELOGS_PACKAGE = "com.gmail.alexflanker89.Lesson_10.changelogs";

    @Bean
    public SpringBootMongock mongock(ApplicationContext springContext, MongoClient mongoClient) {
        return (SpringBootMongock) new SpringBootMongockBuilder(mongoClient, "library", CHANGELOGS_PACKAGE).setApplicationContext(springContext).setLockQuickConfig().build();

    }
}
