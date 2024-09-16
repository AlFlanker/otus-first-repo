package com.gmail.alexflanker89.Lesson_10.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public class CommentDTO {
    @NotBlank(message = "не может быть пустым")
    @Size(max = 20, min = 4, message = "максимальное кол-во символов 4096")
    private String username;
    @Size(max = 4096, message = "максимальное кол-во символов 4096")
    @NotBlank(message = "не может быть пустым")
    private String comment;
}
