package com.gmail.alexflanker89.lesson5.DaoTest;

import com.gmail.alexflanker89.lesson5.dao.interfaces.AuthorRepository;
import com.gmail.alexflanker89.lesson5.dao.interfaces.BookRepository;
import com.gmail.alexflanker89.lesson5.dao.interfaces.GenreRepository;
import com.gmail.alexflanker89.lesson5.dao.repository.AuthorRepositoryJpa;
import com.gmail.alexflanker89.lesson5.dao.repository.BookRepositoryJpa;
import com.gmail.alexflanker89.lesson5.dao.repository.GenreRepositoryJpa;
import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import com.gmail.alexflanker89.lesson5.execptions.BookNotExistExeption;
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
import java.util.Set;
@DisplayName("Тестирование методов репозитария книг")

@ActiveProfiles("test")
@DataJpaTest
@Import({BookRepositoryJpa.class, GenreRepositoryJpa.class, AuthorRepositoryJpa.class})
@ExtendWith(SpringExtension.class)
public class BookRepoTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    BookRepository bookRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    AuthorRepository authorRepository;


    @Test
    @DisplayName("загрузка книг")
    public void loadAll() {
        Assertions.assertTrue(bookRepository.findAll().size() > 0);
    }
    @Test
    @DisplayName("загрузка по всем жанрам")
    public void getByGernes(){
        List<Genre> genres = genreRepository.findAll();
        Assertions.assertEquals(13,bookRepository.findByGernes(new HashSet<>(genres)).size() );
    }

    @Test
    @DisplayName("загрузка по жанру")
    public void getByGerne(){
        List<Genre> genres = genreRepository.findAll();
        Genre genre = genres.get(0);
        Assertions.assertTrue(bookRepository.findByGernes(Collections.singleton(genre)).size() > 0);
    }

    @Test
    @DisplayName("Проверка null")
    public void getByGerneNull(){
        Assertions.assertEquals(0,bookRepository.findByGernes(Collections.singleton(null)).size() );
    }

    @Test
    @DisplayName("Загрузка по несуществующему в PersistContext Жанру")
    public void getByGerneNotFound(){
        Genre genre = new Genre();
        Assertions.assertThrows(BookNotExistExeption.class,()->bookRepository.findByGernes(Collections.singleton(genre)));
    }
    @Test
    @DisplayName("загрузка по всем авторам")
    public void getByAllAuthors(){
        List<Author> authors = authorRepository.findAll();
        Assertions.assertEquals(13,bookRepository.findByAuthors(new HashSet<>(authors)).size());
    }

    @Test
    @DisplayName("загрузка по  автору")
    public void getByAuthor(){
        List<Author> authors = authorRepository.findAll();
        Assertions.assertEquals(3,bookRepository.findByAuthors(Collections.singleton(authors.get(0))).size());
    }

    @Test
    @DisplayName("загрузка по null")
    public void getByAuthorNull(){
        Assertions.assertEquals(0,bookRepository.findByAuthors(Collections.singleton(null)).size());
    }

    @Test
    @DisplayName("Загрузка по несуществующему в PersistContext Автору")
    public void getByAuthorNotFound(){
        Author author = new Author();
        Assertions.assertThrows(BookNotExistExeption.class,()->bookRepository.findByAuthors(Collections.singleton(author)));
    }


    @Test
    @DisplayName("схранение новой книги")
    public void saveTest(){
        Book book = new Book();
        book.setTitle("test book");
        book.setAuthors(Collections.emptySet());
        book.setGenres(Collections.emptySet());
        book.setEdition("bla bla bla");
        book.setReleaseDate(LocalDate.now());
        book.setDescription("bla bla blabla bla blabla bla blabla bla bla");
        bookRepository.save(book);
        Set<Book> byGernes = bookRepository.findByTitle("test book");
        Assertions.assertEquals(1,byGernes.size());

    }

    @Test
    @DisplayName("удаление книги")
    public void deleteTest(){
        Book book = new Book();
        book.setTitle("test book");
        book.setAuthors(Collections.emptySet());
        book.setGenres(Collections.emptySet());
        book.setEdition("bla bla bla");
        book.setReleaseDate(LocalDate.now());
        book.setDescription("bla bla blabla bla blabla bla blabla bla bla");
        bookRepository.save(book);
        Set<Book> byGernes = bookRepository.findByTitle("test book");
         book = byGernes.stream().toArray(Book[]::new)[0];
        bookRepository.delete(book);
        Assertions.assertEquals(Collections.emptySet(),bookRepository.findByTitle("test book"));
    }

    @Test
    @DisplayName("обновление книги")
    public void updateTest(){
        Book book = bookRepository.findById(1);
        book.setReleaseDate(LocalDate.now());
        bookRepository.update(book);

        Assertions.assertEquals(LocalDate.now(),bookRepository.findById(1).getReleaseDate());
    }

    @Test
    @DisplayName("Загрузка по дате. Релиз позже")
    public void findAllByReleaseDateGreaterThanTest(){
        Set<Book> greaterThan = bookRepository.findAllByReleaseDateGreaterThan(LocalDate.of(2004, 5, 5));
        Assertions.assertEquals(12,greaterThan.size());
    }

    @Test
    @DisplayName("Загрузка по дате. Релиз до")
    public void findAllByReleaseDateLessThanTest(){
        Set<Book> lessThan = bookRepository.findAllByReleaseDateLessThan(LocalDate.of(2010, 5, 5));
        Assertions.assertEquals(7,lessThan.size());
    }

    @Test
    @DisplayName("Загрузка по дате. Релиз ")
    public void findAllByReleaseDateTest(){
        Set<Book> books = bookRepository.findAllByReleaseDate(LocalDate.of(2012, 1, 1));
        Assertions.assertEquals(1,books.size());
    }
}
