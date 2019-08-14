package com.gmail.alexflanker89.lesson.controller;

import com.gmail.alexflanker89.lesson.domain.auth.User;
import com.gmail.alexflanker89.lesson.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;


    @PostMapping("/registration")
    public ResponseEntity addUser(@RequestParam("username")String username,@RequestParam("password")String password){

        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        userService.save(u);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
