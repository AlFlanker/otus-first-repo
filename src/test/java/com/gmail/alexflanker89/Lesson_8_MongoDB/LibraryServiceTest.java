package com.gmail.alexflanker89.Lesson_8_MongoDB;

import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Author;
import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Book;
import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Comment;
import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Genre;
import com.gmail.alexflanker89.Lesson_8_MongoDB.repo.AuthorRepo;
import com.gmail.alexflanker89.Lesson_8_MongoDB.repo.GenreRepo;
import com.gmail.alexflanker89.Lesson_8_MongoDB.services.interfaces.LibraryService;
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
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@SpringBootTest(properties = {
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false",
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false"
})
@DisplayName("Проверка сервеса работы с книгами")
@ExtendWith(SpringExtension.class)
public class LibraryServiceTest {
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private GenreRepo genreRepo;
    @Autowired
    private MongoOperations mongoOperations;

    @Test
    @DisplayName("Загрузка всех книг")
    public void loadAll(){
        Assertions.assertTrue(libraryService.getAll().size()>0);
    }
    @Test
    @DisplayName("Загрузка книги по дате")
    public void getAllByReleaseDateTest(){
        Assertions.assertTrue(libraryService.getAllByReleaseDate(LocalDate.of(2009, 1, 1)).size()>0);
    }
    @Test
    @DisplayName("Загрузка книг у которых релиз ранее даты")
    public void getAllByReleaseDateLessThanTest(){
        Assertions.assertTrue(libraryService.getAllByReleaseDateLessThan(LocalDate.now()).size()>0);
    }
    @Test
    @DisplayName("Загрузка книг у которых релиз позже даты")
    public void getAllByReleaseDateGreaterThanTest(){
        Assertions.assertTrue(libraryService.getAllByReleaseDateGreaterThan(LocalDate.of(2008, 12, 31)).size()>0);
    }
    @Test
    @DisplayName("Загрузка книг по авторам")
    public void getAllByAuthorsTest(){
        List<Author> authors = authorRepo.findAll();
        Assertions.assertTrue(libraryService.getAllByAuthors(new HashSet<>(authors)).size()>0);
    }
    @Test
    @DisplayName("Загрузка книг по жанрам")
    public void getAllByGenresTest(){
        List<Genre> genres = genreRepo.findAll();
        Assertions.assertTrue(libraryService.getAllByGenres(new HashSet<>(genres)).size()>0);
    }
    @Test
    @DisplayName("Сохранение книг")
    public void saveTest(){
        Book book = new Book();
        book.setTitle("test");
        libraryService.save(book);
        List<Book> list = mongoOperations.findAllAndRemove(new Query().addCriteria(Criteria.where("title").is("test")), Book.class);
        Assertions.assertTrue(list.size()>0);
    }
    @Test
    @DisplayName("Обновление книг")
    public void updateTest(){
        Book book = new Book();
        book.setTitle("test");
        book.setReleaseDate(LocalDate.now());
        Book save = libraryService.save(book);
        save.setReleaseDate(LocalDate.of(1999,1,1));
        Book update = libraryService.update(book);
        List<Book> books = mongoOperations.find(new Query().addCriteria(Criteria.where("_id").is(update.getId())), Book.class);
        Assertions.assertTrue(books.get(0).getReleaseDate().equals(LocalDate.of(1999,1,1)));
        libraryService.delete(update);

    }

    @Test
    @DisplayName("Поиск автора по имени и фамилиии")
    public void getByNameAndLastnameTest(){
        List<Author> authors = libraryService.getByNameAndLastname("Артур", "Хейли");
        Assertions.assertTrue(authors.size()>0);
    }

    @Test
    @DisplayName("Показать всех авторов")
    public void getAllAuthorsTest(){
        List<Author> authors = libraryService.getAllAuthor();
        Assertions.assertTrue(authors.size()>0);
    }

    @Test
    @DisplayName("Добавить комментарий")
    public void addCommitTest(){
        Comment comment = new Comment();
        comment.setUsername("testUser");
        comment.setComment("Something very long text");
        comment.setCreated(LocalDateTime.now());
        Book book = new Book();
        book.setTitle("test comment");
        libraryService.addCommit(book,comment);
        List<Book> list = mongoOperations.findAllAndRemove(new Query().addCriteria(Criteria.where("title").is("test comment")), Book.class);
        Assertions.assertTrue(list.size()>0);
    }
}
