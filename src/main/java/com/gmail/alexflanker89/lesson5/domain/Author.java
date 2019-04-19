package com.gmail.alexflanker89.lesson5.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"name","lastname","dateOfBirth"})
@ToString(of = {"name","lastname","dateOfBirth"})
@NoArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue

    private long id;

    private String name;

    private String lastname;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors",cascade = CascadeType.REMOVE)
    private Set<Book> books = new HashSet<>();

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    private boolean isDeleted;

}
