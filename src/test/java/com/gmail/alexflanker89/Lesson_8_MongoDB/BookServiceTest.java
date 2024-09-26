package com.gmail.alexflanker89.Lesson_8_MongoDB;

import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Author;
import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Book;
import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Genre;
import com.gmail.alexflanker89.Lesson_8_MongoDB.repo.AuthorRepo;
import com.gmail.alexflanker89.Lesson_8_MongoDB.repo.GenreRepo;
import com.gmail.alexflanker89.Lesson_8_MongoDB.services.interfaces.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(properties = {
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false",
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false"
})
@DisplayName("Проверка сервеса работы с книгами")
@ExtendWith(SpringExtension.class)
public class BookServiceTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private GenreRepo genreRepo;

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    @DisplayName("Загрузка всех книг")
    public void loadAll(){
        Assertions.assertTrue(bookService.getAll().size()>0);
    }
    @Test
    @DisplayName("Загрузка книги по дате")
    public void getAllByReleaseDateTest(){
        Assertions.assertTrue(bookService.getAllByReleaseDate(LocalDate.of(2009, 1, 1)).size()>0);
    }
    @Test
    @DisplayName("Загрузка книг у которых релиз ранее даты")
    public void getAllByReleaseDateLessThanTest(){
        Assertions.assertTrue(bookService.getAllByReleaseDateLessThan(LocalDate.now()).size()>0);
    }
    @Test
    @DisplayName("Загрузка книг у которых релиз позже даты")
    public void getAllByReleaseDateGreaterThanTest(){
        Assertions.assertTrue(bookService.getAllByReleaseDateGreaterThan(LocalDate.of(2008, 12, 31)).size()>0);
    }
    @Test
    @DisplayName("Загрузка книг по авторам")
    public void getAllByAuthorsTest(){
        List<Author> authors = authorRepo.findAll();
        List<Book> byAuthors = bookService.getAllByAuthors(new HashSet<>(authors));
        Assertions.assertTrue(byAuthors.size()>0);
    }
    @Test
    @DisplayName("Загрузка книг по жанрам")
    public void getAllByGenresTest(){
        java.lang.String genres = genreRepo.findAll().stream().map(Genre::getGenreName).collect(Collectors.joining(","));
        Assertions.assertTrue(bookService.getAllByGenres((genres)).size()>0);
    }
    @Test
    @DisplayName("Сохранение книг")
    public void saveTest(){
        Book book = new Book();
        book.setTitle("test");
        bookService.save(book);
        List<Book> list = mongoOperations.findAllAndRemove(new Query().addCriteria(Criteria.where("title").is("test")), Book.class);
        Assertions.assertTrue(list.size()>0);
    }
    @Test
    @DisplayName("Обновление книг")
    public void updateTest(){
        Book book = new Book();
        book.setTitle("test");
        book.setReleaseDate(LocalDate.now());
        Book save = bookService.save(book);
        save.setReleaseDate(LocalDate.of(1999,1,1));
        Book update = bookService.update(book);
        List<Book> books = mongoOperations.find(new Query().addCriteria(Criteria.where("_id").is(update.getId())), Book.class);
        Assertions.assertTrue(books.get(0).getReleaseDate().equals(LocalDate.of(1999,1,1)));
        bookService.delete(update);

    }

    @Test
    @DisplayName("Поиск автора по имени и фамилиии")
    public void getByNameAndLastnameTest(){
        List<Author> authors = bookService.getByNameAndLastname("Артур", "Хейли");
        Assertions.assertTrue(authors.size()>0);
    }

    @Test
    @DisplayName("Показать всех авторов")
    public void getAllAuthorsTest(){
        List<Author> authors = bookService.getAllAuthor();
        Assertions.assertTrue(authors.size()>0);
    }


    @Test
    @DisplayName("Поиск книг по имени и фамилии автора")
    public void getAllByAuthorsNameAndLastnameTest(){
        List<Book> books = bookService.getAllByAuthorsNameAndLastname("Артур", "Хейли");
        Assertions.assertTrue(books.size()>0);
    }

    @Test
    @DisplayName("Автор по id книги")
    public void getAuthorsByBookIdTest(){
        List<Author> authors = bookService.getByBookId(bookService.getAll().get(0).getId());
        Assertions.assertTrue(authors.size()>0);

    }
}
