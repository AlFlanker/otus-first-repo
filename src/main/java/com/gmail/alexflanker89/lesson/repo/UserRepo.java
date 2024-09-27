package com.gmail.alexflanker89.lesson.repo;

import com.gmail.alexflanker89.lesson.domain.auth.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    User findByUsername(String username);

}
