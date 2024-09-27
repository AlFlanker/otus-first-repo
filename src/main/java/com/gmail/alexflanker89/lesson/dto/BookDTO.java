package com.gmail.alexflanker89.lesson.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(of = {"id", "title", "edition", "description", "releaseDate", "genres", "authors"})
@RequiredArgsConstructor
public class BookDTO {
    private String id;
    @Size(min = 2, max = 100, message = "название книги должно содержать от 2 до 100 символов!")
    private String title;
    @Size(max = 100, message = "издательство должно содержать не более 100 символов!")
    private String edition;
    @Size(max = 4096, message = "описание книги не должно привышать 4096 символов!")
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull(message = "не указана дата релиза")
    private LocalDate releaseDate;
    @NotNull(message = "не указан жанр")
    private List<String> genres = new ArrayList<>();
    @NotNull(message = "не указан автор")
    private List<AuthorDTO> authors = new ArrayList<>();
}
