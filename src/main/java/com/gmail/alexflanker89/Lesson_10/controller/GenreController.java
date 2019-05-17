package com.gmail.alexflanker89.Lesson_10.controller;

import com.gmail.alexflanker89.Lesson_10.services.interfaces.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;
    @GetMapping("genres")
    public ResponseEntity getAllGenres(){
        return ResponseEntity.ok(genreService.getAll());
    }
}
