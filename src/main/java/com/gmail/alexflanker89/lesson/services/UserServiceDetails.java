package com.gmail.alexflanker89.lesson.services;

import com.gmail.alexflanker89.lesson.repo.UserRepo;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserServiceDetails implements UserDetailsService {

    private final UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepo.findByUsername(username.trim().toLowerCase());
    }





}
