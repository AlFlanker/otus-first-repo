package com.gmail.alexflanker89.Lesson_8_MongoDB.controller;

import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Author;
import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Book;
import com.gmail.alexflanker89.Lesson_8_MongoDB.services.interfaces.LibraryService;
import com.gmail.alexflanker89.Lesson_8_MongoDB.view.View;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@AllArgsConstructor
@ShellComponent
public class LibraryController {
    private final LibraryService libraryService;
    private final View view;


    /**
     * Поиск п оавтору
     *
     * @param name     Имя автора книги
     * @param lastname Фамилия автора книги
     */

    @ShellMethod(value = "Commands: author --n Name --l lastname", key = "book author")
    public void bookMethod(@ShellOption(value = {"--n", "name"}, defaultValue = "") String name,
                           @ShellOption(value = {"--l", "lastname"}, defaultValue = "") String lastname) {
        Author author = new Author();
        author.setName(name);
        author.setLastname(lastname);


    }

    /**
     * Возвращает краткую инфу о всех книгах
     */
    @ShellMethod(value = "return all book", key = "book")
    public void getAllBook() {
        List<Book> all = libraryService.getAll();
        view.showBooks(all);
    }

    /**
     * Возвращает книги по выбранному жанру
     *
     * @param genre жанр книги
     */
    @ShellMethod(value = "Commands: --g genre", key = "book genre")
    public void genreMethod(@ShellOption(value = {"--g"}, defaultValue = "") String genre) {
        view.showMessage("Введите название жанра");
    }


    @ShellMethod(value = "return all author if book_id =0, else books author(-s)", key = "authors")
    public void getAllAuthor(@ShellOption(value = "--book_id", defaultValue = "0") long id) {
    }

    /**
     * Возвращает жанры выбранной книги, если id =0, то все существующие жанры
     *
     * @param id книги
     */
    @Transactional
    @ShellMethod(value = "Return all genres if book_id !=0, else return chose books genres ", key = "genres")
    public void getAllGenre(@ShellOption(value = "--book_id", defaultValue = "0") long id) {
    }



    /**
     * Оставить комментерий к книге
     *
     * @param id книги
     */
    @ShellMethod(value = "write comment ", key = "comment")
    public void writeComment(@ShellOption(value = "--book_id", defaultValue = "0") long id, @ShellOption(value = "--a") String username, @ShellOption(value = "--c") String text) {

    }

    /**
     * Показать отзывы
     *
     * @param id книги
     */
    @ShellMethod(value = "show comment ", key = "comments")
    public void getComments(@ShellOption(value = "--book_id", defaultValue = "0") long id) {

    }
}
