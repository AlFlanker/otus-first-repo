package com.gmail.alexflanker89.lesson5.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"genreName"})
@ToString(of = {"id","genreName"})
@NoArgsConstructor
@Entity
public class Genre {

    @Id @GeneratedValue
    private long id;

    private String genreName;

    @ManyToMany(mappedBy = "genres",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Book> books = new HashSet<>();

    private boolean isDeleted;

}
