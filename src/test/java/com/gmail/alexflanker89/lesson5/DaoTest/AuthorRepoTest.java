package com.gmail.alexflanker89.lesson5.DaoTest;

import com.gmail.alexflanker89.lesson5.dao.interfaces.AuthorRepository;
import com.gmail.alexflanker89.lesson5.dao.interfaces.BookRepository;
import com.gmail.alexflanker89.lesson5.dao.interfaces.GenreRepository;
import com.gmail.alexflanker89.lesson5.dao.repository.AuthorRepositoryJpa;
import com.gmail.alexflanker89.lesson5.dao.repository.BookRepositoryJpa;
import com.gmail.alexflanker89.lesson5.dao.repository.GenreRepositoryJpa;
import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
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

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@DisplayName("Тестирование методов репозитария авторов")
@ActiveProfiles("test")
@DataJpaTest
@Import({BookRepositoryJpa.class, GenreRepositoryJpa.class, AuthorRepositoryJpa.class})
@ExtendWith(SpringExtension.class)
public class AuthorRepoTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    BookRepository bookRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    AuthorRepository authorRepository;


    @Test
    @DisplayName("загрузка авторов")
    public void loadAll() {
        Assertions.assertEquals(14,authorRepository.findAll().size());
    }

    @Test
    @DisplayName("загрузка по всем книгам")
    public void getByBooks(){
        List<Book> books = bookRepository.findAll();
        List<Author> authors = authorRepository.findByBook(new HashSet<>(books));
        Assertions.assertEquals(14, authors.size() );
    }

    @Test
    @DisplayName("загрузка по  книгt")
    public void getByBook(){
        Book book = bookRepository.findById(1);
        List<Author> authors = authorRepository.findByBook(Collections.singleton(book));
        Assertions.assertEquals(1, authors.size() );
    }


    @Test
    @DisplayName("Проверка null")
    public void getByBooksNull(){
        Assertions.assertEquals(0,authorRepository.findByBook(Collections.singleton(null)).size() );
    }

    @Test
    @DisplayName("Загрузка по несуществующему в PersistContext Книге")
    public void getByGerneNotFound(){
        Book book = new Book();
        Assertions.assertThrows(IllegalStateException.class,()->authorRepository.findByBook(Collections.singleton(book)));
    }



    @Test
    @DisplayName("схранение нового автора")
    public void saveTest(){
        Author author = new Author();
        author.setName("Test name");
        author.setLastname("test lastname");
        authorRepository.save(author);

        Assertions.assertEquals(1, authorRepository.findByNameAndLastname(author.getName(), author.getLastname()).size());

    }

    @Test
    @DisplayName("удаление автора")
    public void deleteTest(){
        Author author = new Author();
        author.setName("Test name");
        author.setLastname("test lastname");
        authorRepository.save(author);
        Author inDB = authorRepository.findByNameAndLastname(author.getName(), author.getLastname()).stream().toArray(Author[]::new)[0];
        authorRepository.delete(inDB);
        Assertions.assertEquals(0, authorRepository.findByNameAndLastname(author.getName(), author.getLastname()).size());
    }

    @Test
    @DisplayName("обновление автора")
    public void updateTest(){
        Author author = authorRepository.findById(1);
        author.setDateOfBirth(LocalDate.now());
        authorRepository.update(author);

        Assertions.assertEquals(LocalDate.now(),authorRepository.findById(1).getDateOfBirth());
    }
}
