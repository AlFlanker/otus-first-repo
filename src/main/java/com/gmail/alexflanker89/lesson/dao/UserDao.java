package com.gmail.alexflanker89.lesson.dao;

import com.gmail.alexflanker89.lesson.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> findAll();
    Optional<User> findByUsernameAndLastName(String username,String lastName);
    void save(User user);
}
