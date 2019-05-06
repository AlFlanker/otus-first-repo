package com.gmail.alexflanker89.Lesson_8_MongoDB.domain;

import com.gmail.alexflanker89.Lesson_8_MongoDB.events.annotations.CascadeDelete;
import com.gmail.alexflanker89.Lesson_8_MongoDB.events.annotations.CascadeSave;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@ToString(of = {"id", "title", "edition", "description"})
@EqualsAndHashCode(of={"title","edition","description","releaseDate"})
@Document
public class Book {
    @Id
    private java.lang.String id;
    private java.lang.String title;
    private java.lang.String edition;
    private java.lang.String description;
    private Set<Languages> languages;
    private LocalDate releaseDate;
    private List<Genre> genres = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private LocalDate created;
    private LocalDate updated;
    @DBRef @CascadeDelete @CascadeSave
    private List<Comment> comments = new ArrayList<>();


}