package com.gmail.alexflanker89.lesson3.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class User {

    private String username;

    private String lastName;

    public User(String username, String lastName) {
        this.username = username;
        this.lastName = lastName;
    }
}
