package com.gmail.alexflanker89.lesson5.IntegrationTests;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.execptions.AuthorNotExistException;
import com.gmail.alexflanker89.lesson5.services.AuthorsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest(properties = {
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false",
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false"
})
@ActiveProfiles("test")
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

    @DisplayName("загрузка старше даты")
    @Test
    public void loadByDateGreaterThanTest(){
        LocalDate localDate = LocalDate.of(1840,5,1);
        List<Author> allByReleaseDateGreaterThan = authorsService.getAllByDateOfBirthGreaterThan(localDate);
        Assertions.assertEquals(13,allByReleaseDateGreaterThan.size());
        Assertions.assertThrows(AuthorNotExistException.class,()->authorsService.getAllByDateOfBirthGreaterThan(null));
    }

    @DisplayName("загрузка младше даты")
    @Test
    public void loadByDateLessThanTest(){
        LocalDate localDate = LocalDate.of(1925,1,1);
        List<Author> byDateOfBirthLessThan = authorsService.getAllByDateOfBirthLessThan(localDate);
        Assertions.assertEquals(2,byDateOfBirthLessThan.size());
        Assertions.assertThrows(AuthorNotExistException.class,()->authorsService.getAllByDateOfBirthLessThan(null));
    }

    @DisplayName("загрузка по дате")
    @Test
    public void loadByDateTEst(){
        LocalDate localDate = LocalDate.of(1840,4,2);
        List<Author> allByDateOfBirth = authorsService.getAllByDateOfBirth(localDate);
        Assertions.assertEquals(1,allByDateOfBirth.size());
    }

   
}
