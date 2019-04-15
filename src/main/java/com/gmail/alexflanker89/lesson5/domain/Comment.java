package com.gmail.alexflanker89.lesson5.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of={"id","username","comment"})
@Data
@Table(name = "comment")
public class Comment {
    @Id @GeneratedValue
    private long id;
    @NotBlank(message = "not be empty")
    private String username;
    @NotBlank(message = "not be empty")
    private String comment;
    @ManyToOne
    private Book book;
    @Column(name = "is_deleted")
    private boolean isDeleted;


    private LocalDateTime updated;

    private LocalDateTime created;
    @PrePersist
    public void toCreate() {
        setCreated(LocalDateTime.now());
    }

    @PreUpdate
    public void toUpdate() {
        setUpdated(LocalDateTime.now());
    }
}
