package com.gmail.alexflanker89.lesson.services.interfaces;

import com.gmail.alexflanker89.lesson.domain.auth.User;

public interface UserService {
    User save(User user);
    Long count();
}
