package com.gmail.alexflanker89.lesson4;

import com.gmail.alexflanker89.lesson4.controller.TestController;
import com.gmail.alexflanker89.lesson4.lifeCycle.TimingExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = {
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false",
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false"

})
@ExtendWith({SpringExtension.class, MockitoExtension.class, TimingExtension.class})
@DisplayName("Класс UserServiceTest")
public class LocalizationConfigTest{

    @Autowired
    private TestController controller;
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private Shell shell;


    @DisplayName("Проверка установки локализации")
    @Test
    public void contextLoads() {
        Object help = shell.evaluate(() -> "setlocal en_US");
        Locale locale = new Locale("en","US");
        Assertions.assertEquals(locale,controller.getLocale());

    }

    @DisplayName("Проверка Русской локализации")
    @Test
    public void localizationTestRu() {
        Locale locale = new Locale("ru", "Ru");

        assertAll("person",
                () -> assertEquals("Уже существует!", messageSource.getMessage("AlreadyExist", null, locale)),
                () -> assertEquals("выбрал", messageSource.getMessage("chose", null, locale)),
                () -> assertEquals("верно", messageSource.getMessage("correct", null, locale)),
                () -> assertEquals("Введите имя и фамилию(через пробел)", messageSource.getMessage("greeting", null, locale)),
                () -> assertEquals("Некорректный ввод", messageSource.getMessage("incorrect", null, locale)),
                () -> assertEquals("повторите ввод", messageSource.getMessage("repeat", null, locale))

        );
    }

        @DisplayName("Проверка Англ локализации")
        @Test
        public void localizationTestEn() {
            Locale locale = new Locale("en", "Us");

            assertAll("person",
                    () -> assertEquals("Already exist", messageSource.getMessage("AlreadyExist", null, locale)),
                    () -> assertEquals("chose", messageSource.getMessage("chose", null, locale)),
                    () -> assertEquals("correct", messageSource.getMessage("correct", null, locale)),
                    () -> assertEquals("Input your name and lastname", messageSource.getMessage("greeting", null, locale)),
                    () -> assertEquals("incorrect input", messageSource.getMessage("incorrect", null, locale)),
                    () -> assertEquals("repeat", messageSource.getMessage("repeat", null, locale))

            );
        }


}
