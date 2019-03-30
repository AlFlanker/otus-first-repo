package com.gmail.alexflanker89.lesson5.IntegrationTests;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import com.gmail.alexflanker89.lesson5.execptions.BookNotExistExeption;
import com.gmail.alexflanker89.lesson5.services.BooksService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
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
public class BookServiceTest {
    @Autowired
    BooksService booksService;

    @Autowired
    private Shell shell;

    @DisplayName("загрузка книг")
    @Test
    public void loadAll() {
        List<Book> all = booksService.getAll();
        Assertions.assertEquals(13, all.size());
    }

    @DisplayName("загрузка по автору книги")
    @Test
    public void loadByAuthorTest() {
        Author author = new Author();
        author.setName("Олег");
        author.setLastname("Дивов");
        List<Book> allByAuthor = booksService.getAllByAuthor(author);
        Assertions.assertEquals(1, allByAuthor.size());
        author.setName("Артур");
        author.setLastname("Хейли");
        allByAuthor = booksService.getAllByAuthor(author);
        Assertions.assertEquals(3, allByAuthor.size());
        Author empty = new Author();
        Assertions.assertThrows(BookNotExistExeption.class,()->booksService.getAllByAuthor(empty));
        Assertions.assertThrows(BookNotExistExeption.class,()->booksService.getAllByAuthor(null));
    }
    @DisplayName("загрузка старше даты")
    @Test
    public void loadByDateGreaterThanTest(){
        LocalDate localDate = LocalDate.of(2018,1,1);
        List<Book> allByReleaseDateGreaterThan = booksService.getAllByReleaseDateGreaterThan(localDate);
        Assertions.assertEquals(4,allByReleaseDateGreaterThan.size());
        Assertions.assertThrows(BookNotExistExeption.class,()->booksService.getAllByReleaseDateGreaterThan(null));
    }

    @DisplayName("загрузка младше даты")
    @Test
    public void loadByDateLessThanTest(){
        LocalDate localDate = LocalDate.of(2010,1,1);
        List<Book> allByReleaseDateLessThan = booksService.getAllByReleaseDateLessThan(localDate);
        Assertions.assertEquals(6,allByReleaseDateLessThan.size());
        Assertions.assertThrows(BookNotExistExeption.class,()-> booksService.getAllByReleaseDateLessThan(null));
    }
    
    @DisplayName("загрузка по дате")
    @Test
    public void loadByDateTEst(){
        LocalDate localDate = LocalDate.of(2009,1,1);
        List<Book> allByReleaseDate = booksService.getAllByReleaseDate(localDate);
        Assertions.assertEquals(4,allByReleaseDate.size());
    }

    @DisplayName("загрузка по жанру")
    @Test
    public void loadByGenreTEst(){
        Genre genre = new Genre();
        genre.setKindOf("Роман");
        List<Book> allByGenre = booksService.getAllByGenre(genre);
        Assertions.assertEquals(6,allByGenre.size());
        Assertions.assertThrows(BookNotExistExeption.class,
                ()->booksService.getAllByGenre(null));
        Assertions.assertThrows(BookNotExistExeption.class,
                ()->booksService.getAllByGenre(new Genre()));


    }

    @DisplayName("сохранение книги")
    @Disabled
    @Test
    public void saveBookTest(){
        Book book = new Book();
        book.setTitle("Элегантная Вселенная");
        book.setDescription("Просто о сложном .......");
        book.setReleaseDate(LocalDate.of(2000,1,1));
        book.setEdition("Дрофа");
        book.setIdDeleted(false);
        Book book1 = booksService.saveBook(book);
        Assertions.assertNotNull(book1);
    }

    @DisplayName("сохранение книги")
    @Disabled
    @Test
    public void updateBookTest(){

        Book book = booksService.getAll().get(0);
        book.setEdition(book.getEdition() +" !!");
        booksService.updateBook(book);

    }



    @DisplayName("Загрузка полной информации о книги")
    @Test
    public void getAllInfoTest(){
        Book book = booksService.getDetailInfoByID(1l);
        Assertions.assertEquals(1,book.getAuthors().size());
        Assertions.assertEquals(1,book.getGenres().size());
    }

    @DisplayName("Загрузка полной информации о книги")
    @Test
    public void getAllInfoNoExistTest(){
        Assertions.assertThrows(BookNotExistExeption.class,() -> booksService.getDetailInfoByID(99l));

    }


}




