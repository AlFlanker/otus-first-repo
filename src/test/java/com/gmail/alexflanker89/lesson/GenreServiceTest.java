package com.gmail.alexflanker89.lesson;

import com.gmail.alexflanker89.lesson.services.interfaces.BookService;
import com.gmail.alexflanker89.lesson.services.interfaces.GenreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Проверка сервеса жанров")
public class GenreServiceTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private GenreService genreService;

    @Test
    @DisplayName("Получить жанры ")
    public void getAllTest(){
        Assertions.assertTrue(genreService.getAll().size()>0);
    }

}
