package com.gmail.alexflanker89.lesson1.domain;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class User {

    private String username;

    private String lastName;

    public User(String username, String lastName) {
        this.username = username;
        this.lastName = lastName;
    }
}
