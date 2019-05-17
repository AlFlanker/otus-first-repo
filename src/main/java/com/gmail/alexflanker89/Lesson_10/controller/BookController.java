package com.gmail.alexflanker89.Lesson_10.controller;

import com.gmail.alexflanker89.Lesson_10.domain.Book;
import com.gmail.alexflanker89.Lesson_10.exceptions.NotFoundException;
import com.gmail.alexflanker89.Lesson_10.services.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping("/books")
    public List<Book> getAllBook(){
        return bookService.getAll();
    }
    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable("id") String id){
        return bookService.getBookById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping("/books/authors")
    public List<Book> getByAuthors(@PathVariable Set<String> authors){
        return null;
    }
}
