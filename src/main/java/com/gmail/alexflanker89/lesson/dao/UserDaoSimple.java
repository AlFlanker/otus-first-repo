package com.gmail.alexflanker89.lesson.dao;

import com.gmail.alexflanker89.lesson.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class UserDaoSimple implements UserDao {
    private List<User> users = new ArrayList<User>();


    @Override
    public List<User> findAll() {
        return users;
    }

    public Optional<User> findByUsernameAndLastName(String username, String lastName) {
        return users.stream().filter(u -> u.getUsername().equals(username) && u.getLastName().equals(lastName)).findFirst();
    }

    @Override
    public void save(User user) {
        users.add(user);
    }
}
