package com.gmail.alexflanker89.lesson5.DaoTest;

import com.gmail.alexflanker89.lesson5.dao.interfaces.BookRepository;
import com.gmail.alexflanker89.lesson5.dao.interfaces.GenreRepository;
import com.gmail.alexflanker89.lesson5.dao.repository.AuthorRepositoryJpa;
import com.gmail.alexflanker89.lesson5.dao.repository.BookRepositoryJpa;
import com.gmail.alexflanker89.lesson5.dao.repository.GenreRepositoryJpa;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@DisplayName("Тестирование методов репозитария жанров")
@ActiveProfiles("test")
@DataJpaTest
@Import({BookRepositoryJpa.class, GenreRepositoryJpa.class, AuthorRepositoryJpa.class})
@ExtendWith(SpringExtension.class)
public class GenreRepoTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    BookRepository bookRepository;

    @Autowired
    GenreRepository genreRepository;




    @Test
    @DisplayName("загрузка жанров")
    public void loadAll() {
        Assertions.assertTrue(genreRepository.findAll().size() > 0);
    }

    @Test
    @DisplayName("загрузка по книге")
    public void getByGernes(){
        Book book = bookRepository.findById(1);
        Assertions.assertEquals(1,genreRepository.findByBook(book).size() );
    }

    @Test
    @DisplayName("загрузка по названию")
    public void getByGerneName(){
        Genre genre = genreRepository.findByName("Роман");
        Assertions.assertNotNull( genre );
    }


}
