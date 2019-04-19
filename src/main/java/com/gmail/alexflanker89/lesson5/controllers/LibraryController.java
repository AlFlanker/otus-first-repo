package com.gmail.alexflanker89.lesson5.controllers;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Comment;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import com.gmail.alexflanker89.lesson5.services.interdaces.AuthorsService;
import com.gmail.alexflanker89.lesson5.services.interdaces.BooksService;
import com.gmail.alexflanker89.lesson5.services.interdaces.CommentService;
import com.gmail.alexflanker89.lesson5.services.interdaces.GenresService;
import com.gmail.alexflanker89.lesson5.view.View;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@ShellComponent
public class LibraryController {
    private final AuthorsService authorsService;
    private final BooksService booksService;
    private final GenresService genresService;
    private final CommentService commentService;
    private final View view;


    /**
     * Поиск по автору
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
        Set<Author> authors = authorsService.getByNameAndLastname(name, lastname);
        if (authors.size() == 0) {
            view.showMessage("Нет книг данного автора ");
        } else {
            Set<Book> allByAuthor = booksService.getAllByAuthors(authors);
            view.showBooks(allByAuthor);
        }
    }

    /**
     * Возвращает краткую инфу о всех книгах
     */
    @ShellMethod(value = "return all book", key = "book")
    public void getAllBook() {
        List<Book> all = booksService.getAll();
        view.showBooks(all);
    }

    /**
     * Возвращает книги по выбранному жанру
     *
     * @param genre жанр книги
     */
    @ShellMethod(value = "Commands: --g genre", key = "book genre")
    public void genreMethod(@ShellOption(value = {"--g"}, defaultValue = "") String genre) {
        if (!StringUtils.isEmpty(genre)) {
            Genre g = genresService.getByGenreName(genre);
            if(g ==null) view.showMessage("нет такого жанра");
            else view.showBooks(booksService.getAllByGenres(Collections.singleton(g)));
        }
        else view.showMessage("Введите название жанра");
    }


    @ShellMethod(value = "return all author if book_id =0, else books author(-s)", key = "authors")
    public void getAllAuthor(@ShellOption(value = "--book_id", defaultValue = "0") long id) {
        if (id == 0) {
            view.showAuthors(authorsService.getAll());
        } else {
            Book book = booksService.getById(id);
            if(book == null) view.showMessage("нет такой книги или не нет информации о авторах");
            else view.showAuthors(authorsService.getByBooks(Collections.singleton(book)));
        }
    }

    /**
     * Возвращает жанры выбранной книги, если id =0, то все существующие жанры
     *
     * @param id книги
     */
    @ShellMethod(value = "Return all genres if book_id !=0, else return chose books genres ", key = "genres")
    public void getAllGenre(@ShellOption(value = "--book_id", defaultValue = "0") long id) {
        if (id == 0) view.showGenres(genresService.getAll());
        else {
            Book book = booksService.getById(id);
            if(book ==null) view.showMessage("Книга не найдена");
            else view.showGenres(genresService.getAllByBook(book));
        }
    }

    /**
     * Показать полную инфу о книги (авторы/жанры/описание)
     *
     * @param id книги
     */
    @ShellMethod(value = "return books Description --id", key = "description")
    public void getBookDescription(@ShellOption(value = "--id") long id) {
        Book book = booksService.getById(id);
        if(book ==null) view.showMessage("Книга не найдена");
        else view.showBook(book);
    }

    /**
     * Оставить комментерий к книге
     *
     * @param id книги
     */
    @ShellMethod(value = "write comment ", key = "comment")
    public void writeComment(@ShellOption(value = "--book_id", defaultValue = "0") long id, @ShellOption(value = "--a") String username, @ShellOption(value = "--c") String text) {
        if (id > 0) {
            Book book = booksService.getById(id);
            if (book != null) {
                Comment comment = new Comment();
                comment.setBook(book);
                comment.setComment(text);
                comment.setUsername(username);
                commentService.save(comment);
            }
        } else view.showMessage("Введите корректный id");
    }

    /**
     * Показать отзывы
     *
     * @param id книги
     */
    @ShellMethod(value = "show comment ", key = "comments")
    public void getComments(@ShellOption(value = "--book_id", defaultValue = "0") long id) {
        if (id > 0) {
            Book book = booksService.getById(id);
            if (book != null) {
                List<Comment> comments = commentService.getByBook(book);
                view.showComments(comments);
            }
            else view.showMessage("Книга не найдена");
        } else view.showMessage("Введите корректный id");
    }
}
