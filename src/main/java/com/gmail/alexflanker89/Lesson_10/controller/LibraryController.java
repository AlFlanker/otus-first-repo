package com.gmail.alexflanker89.Lesson_10.controller;

import com.gmail.alexflanker89.Lesson_10.services.interfaces.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LibraryController {
    private final BookService bookService;

    public LibraryController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String main(Model model){
        model.addAttribute("isDevMode",true);
        return "library";
    }


}
