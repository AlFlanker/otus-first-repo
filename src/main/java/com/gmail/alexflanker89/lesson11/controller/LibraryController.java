package com.gmail.alexflanker89.lesson11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LibraryController {
    @GetMapping
    public String main(Model model) {
        model.addAttribute("isDevMode", true);
        return "library";
    }


}
