package com.gmail.alexflanker89.lesson5.IntegrationTests;

import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import com.gmail.alexflanker89.lesson5.execptions.GenreNotExistException;
import com.gmail.alexflanker89.lesson5.services.interdaces.GenresService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest(properties = {
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false",
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false"
})

@ExtendWith(SpringExtension.class)
public class GenreServiceTest {
    @Autowired
    private Shell shell;
    @Autowired
    private GenresService genresService;

    @DisplayName("загрузка жанров")
    @Test
    public void loadAll() {
        List<Genre> all = genresService.getAll();
        Assertions.assertEquals(5, all.size());
    }

    @DisplayName("загрузка по книге")
    @Test
    public void loadByBook() {
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,()->genresService.getAllByBook(new Book()));
        Assertions.assertThrows(GenreNotExistException.class,()->genresService.getAllByBook(null));
    }
}
