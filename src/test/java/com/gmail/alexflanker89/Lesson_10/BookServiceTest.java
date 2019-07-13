package com.gmail.alexflanker89.Lesson_10;

import com.gmail.alexflanker89.Lesson_10.domain.Author;
import com.gmail.alexflanker89.Lesson_10.domain.Book;
import com.gmail.alexflanker89.Lesson_10.repo.AuthorRepo;
import com.gmail.alexflanker89.Lesson_10.services.interfaces.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
@DisplayName("Проверка сервеса работы с книгами")
@ExtendWith(SpringExtension.class)
public class BookServiceTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorRepo authorRepo;

    @Test
    @DisplayName("Загрузка всех книг")
    public void loadAll() {
        List<Book> all = bookService.getAll();
        Assertions.assertTrue(all.size() > 0);
    }

    @Test
    @DisplayName("Загрузка книг у которых релиз ранее даты")
    public void getAllByReleaseDateLessThanTest() {
        Assertions.assertTrue(bookService.getAllByReleaseDateLessThan(LocalDate.now()).size() > 0);
    }

    @Test
    @DisplayName("Загрузка книг у которых релиз позже даты")
    public void getAllByReleaseDateGreaterThanTest() {
        Assertions.assertTrue(bookService.getAllByReleaseDateGreaterThan(LocalDate.of(2008, 12, 31)).size() > 0);
    }

    @Test
    @DisplayName("Загрузка книг по авторам")
    public void getAllByAuthorsTest() {
        List<Author> authors = authorRepo.findAll();
        List<Book> byAuthors = bookService.getAllByAuthors(new HashSet<>(authors));
        Assertions.assertTrue(byAuthors.size() > 0);
    }


    @Test
    @DisplayName("Поиск автора по имени и фамилиии")
    public void getByNameAndLastnameTest() {
        List<Author> authors = bookService.getByNameAndLastname("Артур", "Хейли");
        Assertions.assertTrue(authors.size() > 0);
    }

    @Test
    @DisplayName("Показать всех авторов")
    public void getAllAuthorsTest() {
        List<Author> authors = bookService.getAllAuthor();
        Assertions.assertTrue(authors.size() > 0);
    }
}

