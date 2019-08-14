package com.gmail.alexflanker89.lesson.controller;

import com.gmail.alexflanker89.lesson.domain.Author;
import com.gmail.alexflanker89.lesson.dto.AuthorDTO;
import com.gmail.alexflanker89.lesson.exceptions.author.AuthorAlreadyExistExceptions;
import com.gmail.alexflanker89.lesson.services.interfaces.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthorsController {
    private final AuthorService authorService;

    @GetMapping("/authors")
    public List<Author> getAuthors() {
        return authorService.getAll();
    }

    @PostMapping("/authors")
    public ResponseEntity addAuthor(@RequestBody @Valid AuthorDTO authorDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtil.getErrorsMap(bindingResult);
            return ResponseEntity.ok(errorsMap);
        }
        try {
            Author author = authorService.save(authorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(author);
        } catch (AuthorAlreadyExistExceptions e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }
}
