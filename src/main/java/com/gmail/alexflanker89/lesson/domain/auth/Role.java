package com.gmail.alexflanker89.lesson.domain.auth;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, ANONYMOUS;

    @Override
    public String getAuthority() {
        return name();
    }
}
