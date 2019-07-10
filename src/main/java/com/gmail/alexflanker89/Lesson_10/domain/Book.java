package com.gmail.alexflanker89.Lesson_10.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.gmail.alexflanker89.Lesson_10.events.annotations.CascadeDelete;
import com.gmail.alexflanker89.Lesson_10.events.annotations.CascadeSave;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@ToString(of = {"id", "title", "edition", "description"})
@EqualsAndHashCode(of = {"title", "edition", "description", "releaseDate_begin"})
@Document
public class Book {
    @Id
    private String id;
    private String title;
    private String edition;
    private String description;
    private Set<Languages> languages;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate releaseDate;
    @DBRef
    @CascadeSave
    private List<Genre> genres = new ArrayList<>();
    @DBRef
    @CascadeSave
    private List<Author> authors = new ArrayList<>();
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate created;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate updated;
    @DBRef
    @CascadeDelete
    @CascadeSave
    private List<Comment> comments = new ArrayList<>();


}