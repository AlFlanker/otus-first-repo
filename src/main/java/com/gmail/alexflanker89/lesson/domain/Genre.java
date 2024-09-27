package com.gmail.alexflanker89.lesson.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(of = {"genreName"})
@ToString(of = {"id", "genreName"})
@NoArgsConstructor
@Document
public class Genre {
    @Id
    private String id;
    private String genreName;
}
