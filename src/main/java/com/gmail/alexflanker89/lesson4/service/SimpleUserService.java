package com.gmail.alexflanker89.lesson4.service;

import com.gmail.alexflanker89.lesson4.dao.UserDao;
import com.gmail.alexflanker89.lesson4.domain.User;
import com.gmail.alexflanker89.lesson4.exceptions.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SimpleUserService implements UserService {
    private UserDao userDao;

    @Autowired
    public SimpleUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(User user) throws IllegalArgumentException, UserAlreadyExistException {

        if (StringUtils.isEmpty(user.getUsername())) throw new IllegalArgumentException("username cannot be null or empty");
        if (StringUtils.isEmpty(user.getLastName())) throw new IllegalArgumentException("lastname cannot be null or empty");
        if (userDao.findByUsernameAndLastName(user.getUsername(), user.getLastName()).isPresent()) {
            throw new UserAlreadyExistException("user already exist");
        } else {
            userDao.save(user);
        }

    }
}
