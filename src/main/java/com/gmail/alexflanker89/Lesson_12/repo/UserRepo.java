package com.gmail.alexflanker89.Lesson_12.repo;

import com.gmail.alexflanker89.Lesson_12.domain.auth.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    User findByUsername(String username);

}
