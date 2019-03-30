package com.gmail.alexflanker89.lesson5.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class Author {
    private long id;
    private String name;
    private String Lastname;
    private LocalDate dateOfBirth;
    private boolean idDeleted;

}
