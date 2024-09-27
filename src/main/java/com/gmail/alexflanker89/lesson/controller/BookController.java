package com.gmail.alexflanker89.lesson.controller;

import com.gmail.alexflanker89.lesson.domain.Book;
import com.gmail.alexflanker89.lesson.dto.BookDTO;
import com.gmail.alexflanker89.lesson.dto.criteria.RequestParams;
import com.gmail.alexflanker89.lesson.exceptions.NotFoundException;
import com.gmail.alexflanker89.lesson.exceptions.book.BookNotFoundExceptions;
import com.gmail.alexflanker89.lesson.services.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/book")
    public List<Book> getAllBook() {

        return bookService.getAll();
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable("id") String id) {
        return bookService.getBookById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/book/params")
    public List<Book> getByParams(@RequestBody RequestParams params) {
        return bookService.getByParams(params);
    }

    @PostMapping("/book")
    public ResponseEntity addBook(@RequestBody @Valid BookDTO bookDTO, BindingResult bindingResult) {
        Book book;
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtil.getErrorsMap(bindingResult);
            return ResponseEntity.ok(errorsMap);
        }
        book = bookService.addBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PutMapping("/book/{id}")
    public ResponseEntity update(@PathVariable("id") Book book, @RequestBody @Valid BookDTO bookDTO, BindingResult bindingResult) {
        Book updateBook;
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtil.getErrorsMap(bindingResult);
            return ResponseEntity.ok(errorsMap);
        }
        try {
            updateBook = bookService.updateBook(book, bookDTO);
        } catch (BookNotFoundExceptions e) {
            log.warn("RuntimeExceptions " + e.toString());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(updateBook);
    }
}
