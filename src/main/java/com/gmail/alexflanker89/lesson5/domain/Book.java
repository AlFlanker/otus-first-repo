package com.gmail.alexflanker89.lesson5.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@ToString(of = {"id","title","edition","description"})
public class Book {
    @Id @GeneratedValue
    private long id;

    private String title;

    private String edition;

    private String description;

    @ElementCollection(targetClass = Languages.class,fetch = FetchType.EAGER)
    @CollectionTable(name="book_language",joinColumns = @JoinColumn(name="book_id"))
    @Enumerated(EnumType.STRING)
    private Set<Languages> languages;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @ManyToMany(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_genre",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") }
    )
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id") }
    )
    private Set<Author> authors = new HashSet<>();

    @Column(name="created",updatable = false)
    private LocalDateTime created;

    @Column(name = "updated",insertable = false)
    private LocalDateTime updated;

    @PrePersist
    public void toCreate() {
        setCreated(LocalDateTime.now());
    }

    @PreUpdate
    public void toUpdate() {
        setUpdated(LocalDateTime.now());
    }

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new HashSet<>();


    private boolean isDeleted;



}
