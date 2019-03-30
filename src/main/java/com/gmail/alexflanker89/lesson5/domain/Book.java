package com.gmail.alexflanker89.lesson5.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class Book {
    private long id;
    private String title;
    private String edition;
    private String description;
    private Set<Languages> languages;
    private LocalDate releaseDate;
    private Set<Genre> genres;
    private Set<Author> authors;
    private boolean idDeleted;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", edition='" + edition + '\'' +
                ", description='" + description + '\'' +
                ", languages=" + languages +
                ", releaseDate=" + releaseDate +
                ", genres=" + genres +
                ", authors=" + authors +
                ", idDeleted=" + idDeleted +
                '}';
    }
}
