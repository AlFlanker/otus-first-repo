package com.gmail.alexflanker89.Lesson_10.controller;

import com.gmail.alexflanker89.Lesson_10.dto.CommentDTO;
import com.gmail.alexflanker89.Lesson_10.exceptions.book.BookNotFoundExceptions;
import com.gmail.alexflanker89.Lesson_10.services.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/{id}")
    public ResponseEntity addComment(@PathVariable("id") String id, @Valid @RequestBody CommentDTO comment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ControllerUtil.getErrorsMap(bindingResult));
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(id, comment));
        } catch (BookNotFoundExceptions e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("book", "BookNotFoundExceptions"));
        }


    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity removeComment(@PathVariable("id") String id, @RequestBody String comment) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(commentService.removeComment(id, comment));
        } catch (BookNotFoundExceptions e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("book", "BookNotFoundExceptions"));
        }
    }
}
