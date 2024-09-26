package com.gmail.alexflanker89.Lesson_8_MongoDB.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = {"name","lastname","dateOfBirth"})
@ToString(of = {"name","lastname","dateOfBirth"})
@NoArgsConstructor
@Document
@CompoundIndexes({
        @CompoundIndex(name = "name_lastname", def = "{'lastName': 1, 'name': 1}")
})
public class Author {
    @Id
    private String id;
    private String name;
    private String lastname;
    private LocalDate dateOfBirth;

    public Author(String  name, String  lastname, LocalDate dateOfBirth) {
        this.name = name;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
    }
}