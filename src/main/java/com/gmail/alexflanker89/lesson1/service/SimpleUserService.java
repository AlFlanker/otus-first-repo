package com.gmail.alexflanker89.lesson1.service;


import com.gmail.alexflanker89.lesson1.dao.UserDao;
import com.gmail.alexflanker89.lesson1.domain.User;


public class SimpleUserService implements UserService {
    private UserDao userDao;

    public SimpleUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(User user) throws IllegalArgumentException {
        if ("".equals(user.getUsername())) throw new IllegalArgumentException();
        if ("".equals(user.getLastName())) throw new IllegalArgumentException();
        if (userDao.findByUsernameAndLastName(user.getUsername(), user.getLastName()).isPresent()) {
            throw new IllegalArgumentException();
        } else {
            userDao.save(user);
        }

    }
}
