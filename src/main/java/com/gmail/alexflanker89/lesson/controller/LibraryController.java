package com.gmail.alexflanker89.lesson.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.gmail.alexflanker89.lesson.domain.auth.User;
import com.gmail.alexflanker89.lesson.domain.auth.view.UserView;
import com.gmail.alexflanker89.lesson.dto.auth.FronendData;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/")
public class LibraryController {

    @JsonView(UserView.FullProfile.class)
    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User userDetails) {
        FronendData fronendData = new FronendData();
        if(Objects.nonNull(userDetails)) {
            fronendData.setUsername(userDetails.getUsername());
            fronendData.setRoles(userDetails.getRoles());
        }else {
            fronendData = null;
        }
        model.addAttribute("isDevMode", true);
        model.addAttribute("frontendData",fronendData);
        return "library";
    }


}
