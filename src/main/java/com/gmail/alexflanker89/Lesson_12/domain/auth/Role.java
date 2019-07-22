package com.gmail.alexflanker89.Lesson_12.domain.auth;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, ANONYMOUSE;

    @Override
    public String getAuthority() {
        return name();
    }
}
