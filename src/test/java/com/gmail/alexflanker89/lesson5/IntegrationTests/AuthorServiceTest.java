package com.gmail.alexflanker89.lesson5.IntegrationTests;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.services.interdaces.AuthorsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class AuthorServiceTest {
    
    @Autowired
    AuthorsService authorsService;

    @Autowired
    private Shell shell;

    @DisplayName("загрузка авторов")
    @Test
    public void loadAll() {
        List<Author> all = authorsService.getAll();
        Assertions.assertEquals(14, all.size());
    }



   
}
