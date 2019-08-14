package com.gmail.alexflanker89.lesson.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "username", "comment"})
@Data
@Document
public class Comment {
    @Id
    private String id;
    private String username;
    private String comment;
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updated;
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime created;


}