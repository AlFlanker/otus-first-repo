package com.gmail.alexflanker89.lesson4;

import com.gmail.alexflanker89.lesson4.lifeCycle.TimingExtension;
import com.gmail.alexflanker89.lesson4.dao.UserDao;
import com.gmail.alexflanker89.lesson4.domain.User;
import com.gmail.alexflanker89.lesson4.exceptions.UserAlreadyExistException;
import com.gmail.alexflanker89.lesson4.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 */
@ExtendWith({SpringExtension.class, MockitoExtension.class, TimingExtension.class})
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Класс UserServiceTest")
public class UserServiceTest {

    @Autowired
     UserService userService;


    @MockBean
    private UserDao userDao;

    @DisplayName("Тест сохранения пользователя")
    @Test
    public void userSave() {
        User user = new User("Name","LastName");
        userService.save(user);
        Mockito.doReturn(Optional.empty())
                .when(userDao)
                .findByUsernameAndLastName(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(userDao,Mockito.times(1))
                .findByUsernameAndLastName(user.getUsername(),user.getLastName());
        Mockito.verify(userDao,Mockito.times(1))
                .save(user);


    }
    @DisplayName("тест на наличия дубликатов")
    @Test
    public void userServiceDublicateTest() {
        User u1 = new User("S", "R");
        Mockito.doReturn(Optional.of(u1))
                .when(userDao)
                .findByUsernameAndLastName(Mockito.anyString(), Mockito.anyString());
        assertThrows(UserAlreadyExistException.class,
                () -> {
                    userService.save(u1);
                });
    }
    @DisplayName("тест на некорректные данные")
    @Test
    public void userServiceSaveEmptyTest() {
        User u1 = new User("", "R");
        assertThrows(IllegalArgumentException.class,
                () -> {
                    userService.save(u1);
                });

    }
    @DisplayName("тест на некорректные данные Null")
    @Test
    public void userServiceSaveNullTest() {
        User u1 = new User(null, "R");
        assertThrows(IllegalArgumentException.class,
                () -> {
                    userService.save(u1);
                });
    }


}
