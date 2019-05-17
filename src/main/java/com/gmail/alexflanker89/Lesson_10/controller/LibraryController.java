package com.gmail.alexflanker89.Lesson_10.controller;

import com.gmail.alexflanker89.Lesson_10.domain.Book;
import com.gmail.alexflanker89.Lesson_10.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class LibraryController {
    private final BookService bookService;
    @Value("${spring.profiles.active}")
    private String dev;
    public LibraryController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("library")
    public String main(Model model){
        List<Book> books = bookService.getAll();
        HashMap<Object, Object> data = new HashMap<>();
        model.addAttribute("isDevMode","dev".equals(dev));
        return "main";
    }
    @GetMapping("/test")
    public String test(Model model){
        return "index";
    }
}
