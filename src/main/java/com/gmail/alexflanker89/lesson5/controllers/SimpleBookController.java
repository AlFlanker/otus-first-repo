package com.gmail.alexflanker89.lesson5.controllers;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import com.gmail.alexflanker89.lesson5.execptions.BookNotExistExeption;
import com.gmail.alexflanker89.lesson5.services.AuthorsService;
import com.gmail.alexflanker89.lesson5.services.BooksService;
import com.gmail.alexflanker89.lesson5.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class SimpleBookController implements BookContoller {
    private final BooksService booksService;
    private final AuthorsService authorsService;
    private  final View view;

    //для наглядности!
    @Autowired
    public SimpleBookController(BooksService booksService, AuthorsService authorsService, View view) {
        this.booksService = booksService;
        this.authorsService = authorsService;
        this.view = view;
    }

    /**
     * Поиск п оавтору
     * @param name Имя автора книги
     * @param lastname Фамилия автора книги
     */
    @ShellMethod(value = "Commands: author --n Name --l lastname",key="book author")
    public void bookMethod(@ShellOption(value = {"--n","name"},defaultValue = "")String name,
                           @ShellOption(value = {"--l","lastname"},defaultValue = "")String lastname) {
        Author author = new Author();
        author.setName(name);
        author.setLastname(lastname);
        try {
            List<Book> allByAuthor = booksService.getAllByAuthor(author);
            view.showBooks(allByAuthor);
        }catch (BookNotExistExeption e){
            view.showMessage(e.getMessage());
        }
    }

    /**
     * Возвращает краткую инфу о всех книгах
     */
    @ShellMethod(value = "return all book",key="book")
    public void getAllBook() {
        List<Book> all = booksService.getAll();
        view.showBooks(all);
    }

    /**
     * Показать полную инфу о книги (авторы/жанры/описание)
     * @param id книги
     */
    @ShellMethod(value = "return books Description",key="description")
    public void getBookDescription(@ShellOption(value = "--id")long id){
        try {
            view.showBook(booksService.getDetailInfoByID(id));
        }
        catch (BookNotExistExeption e){
            view.showMessage(e.getMessage());
        }
    }

    /**
     * Возвращает книги по выбранному жанру
     * @param genre жанр книги
     */
    @ShellMethod(value = "Commands: --g genre",key="book genre")
    public void genreMethod(@ShellOption(value = {"--g"},defaultValue = "") String genre){
        Genre g = new Genre();
        g.setKindOf(genre);
        try {
            view.showBooks(booksService.getAllByGenre(g));
        }catch (BookNotExistExeption e){
            view.showMessage(e.getMessage());
        }

    }
}
