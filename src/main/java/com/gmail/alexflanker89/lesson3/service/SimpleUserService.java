package com.gmail.alexflanker89.lesson3.service;

import com.gmail.alexflanker89.lesson3.dao.UserDao;
import com.gmail.alexflanker89.lesson3.domain.User;
import com.gmail.alexflanker89.lesson3.exceptions.UserAlreadyExistException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SimpleUserService implements UserService {
    private UserDao userDao;

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
