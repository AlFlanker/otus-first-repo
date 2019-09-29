package com.gmail.alexflanker89.lesson.services.endpoints;


import com.gmail.alexflanker89.lesson.services.interfaces.AuthorService;
import com.gmail.alexflanker89.lesson.services.interfaces.BookService;
import com.gmail.alexflanker89.lesson.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@WebEndpoint(id = "library")
@RequiredArgsConstructor
public class LibraryStatusEndpoint {
    private final BookService bookService;
    private final AuthorService authorService;
    private final UserService userService;
    private final SessionRegistry sessionRegistry;

    @ReadOperation
    public ResponseEntity get () {
        return ResponseEntity.ok(getinfo());
    }

    private Map<String, String> getinfo() {
        Map info = new HashMap<String, String>();
        info.put("число книг", bookService.count());
        info.put("число авторов", authorService.count());
        info.put("число зарегистрированных пользователей", userService.count());
        info.put("активный сессий", sessionRegistry.getAllPrincipals().size());
        return info;
    }

}
