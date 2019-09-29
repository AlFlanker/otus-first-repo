package com.gmail.alexflanker89.lesson.services;

import com.gmail.alexflanker89.lesson.domain.auth.Role;
import com.gmail.alexflanker89.lesson.domain.auth.User;
import com.gmail.alexflanker89.lesson.exceptions.auth.UserAlreadyExistExeption;
import com.gmail.alexflanker89.lesson.repo.UserRepo;
import com.gmail.alexflanker89.lesson.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;
    @Override
    public User save(User user) {
        if(Objects.nonNull(userRepo.findByUsername(user.getUsername()))){
            throw new UserAlreadyExistExeption();
        }

        user.setActive(true);
        user.setRoles(user.getUsername().equalsIgnoreCase("Admin")? Collections.singleton(Role.ADMIN):Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepo.save(user);

    }

    @Override
    public Long count() {
        return userRepo.count();
    }
}
