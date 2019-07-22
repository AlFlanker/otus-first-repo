package com.gmail.alexflanker89.Lesson_12.dto.auth;

import com.gmail.alexflanker89.Lesson_12.domain.auth.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class FronendData {
    private String username;
    private Set<Role> roles;

}
