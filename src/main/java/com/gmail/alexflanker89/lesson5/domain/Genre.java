package com.gmail.alexflanker89.lesson5.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Genre {
    private long id;
    private String kindOf;
    private boolean idDeleted;

}
