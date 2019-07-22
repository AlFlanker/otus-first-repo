package com.gmail.alexflanker89.Lesson_12.services;

import com.gmail.alexflanker89.Lesson_12.domain.auth.Role;
import com.gmail.alexflanker89.Lesson_12.domain.auth.User;
import com.gmail.alexflanker89.Lesson_12.exceptions.auth.UserAlreadyExistExeption;
import com.gmail.alexflanker89.Lesson_12.repo.UserRepo;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Data
@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username.trim().toLowerCase());
    }

    public User save(User user) {
        if(Objects.nonNull(userRepo.findByUsername(user.getUsername()))){
            throw new UserAlreadyExistExeption();
        }

        user.setActive(true);
        user.setRoles(user.getUsername().equalsIgnoreCase("Admin")?Collections.singleton(Role.ADMIN):Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepo.save(user);

    }



}
