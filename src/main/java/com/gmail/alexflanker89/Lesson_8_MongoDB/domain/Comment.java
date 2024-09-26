package com.gmail.alexflanker89.Lesson_8_MongoDB.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@NoArgsConstructor
@EqualsAndHashCode(of={"id","username","comment"})
@Data
@Document
public class Comment {
    @Id
    private String id;
    private String username;
    private String comment;
    private LocalDateTime updated;
    private LocalDateTime created;


}