package com.gmail.alexflanker89.lesson5.controllers;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Comment;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import com.gmail.alexflanker89.lesson5.execptions.AuthorNotExistException;
import com.gmail.alexflanker89.lesson5.execptions.BookNotExistExeption;
import com.gmail.alexflanker89.lesson5.execptions.GenreNotExistException;
import com.gmail.alexflanker89.lesson5.services.interdaces.AuthorsService;
import com.gmail.alexflanker89.lesson5.services.interdaces.BooksService;
import com.gmail.alexflanker89.lesson5.services.interdaces.CommentService;
import com.gmail.alexflanker89.lesson5.services.interdaces.GenresService;
import com.gmail.alexflanker89.lesson5.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@ShellComponent
public class LibraryController {
    private final AuthorsService authorsService;
    private final BooksService booksService;
    private final GenresService genresService;
    private final CommentService commentService;
    private final View view;

    @Autowired
    public LibraryController(AuthorsService authorsService, BooksService booksService, GenresService genresService, CommentService commentService, View view) {
        this.authorsService = authorsService;
        this.booksService = booksService;
        this.genresService = genresService;
        this.commentService = commentService;
        this.view = view;
    }

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
        Set<Author> authors = authorsService.getByNameAndLastname(name, lastname);
        try {
            Set<Book> allByAuthor = booksService.getAllByAuthors(authors);
            view.showBooks(allByAuthor);
        } catch (BookNotExistExeption e) {
            view.showMessage(e.getMessage());
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
            try {
                view.showBooks(booksService.getAllByGenres(Collections.singleton(g)));
            } catch (BookNotExistExeption e) {
                view.showMessage(e.getMessage());
            }
        }
        view.showMessage("Введите название жанра");
    }


    @ShellMethod(value = "return all author if book_id =0, else books author(-s)", key = "authors")
    public void getAllAuthor(@ShellOption(value = "--book_id", defaultValue = "0") long id) {
        try {
            if (id == 0) {
                view.showAuthors(authorsService.getAll());
            } else {
                Book book = booksService.getById(id);
                view.showAuthors(authorsService.getByBooks(Collections.singleton(book)));

            }
        } catch (AuthorNotExistException e) {
            view.showMessage(e.getMessage());
        }
    }

    /**
     * Возвращает жанры выбранной книги, если id =0, то все существующие жанры
     *
     * @param id книги
     */
    @Transactional
    @ShellMethod(value = "Return all genres if book_id !=0, else return chose books genres ", key = "genres")
    public void getAllGenre(@ShellOption(value = "--book_id", defaultValue = "0") long id) {
        try {
            if (id == 0) view.showGenres(genresService.getAll());
            else {
                Book book = booksService.getById(id);
                view.showGenres(book.getGenres());
            }
        } catch (GenreNotExistException e) {
            view.showMessage(e.getMessage());
        }
    }

    /**
     * Показать полную инфу о книги (авторы/жанры/описание)
     *
     * @param id книги
     */
    @ShellMethod(value = "return books Description --id", key = "description")
    public void getBookDescription(@ShellOption(value = "--id") long id) {
        try {
            Book book = booksService.getById(id);
            view.showBook(book);
        } catch (BookNotExistExeption e) {
            view.showMessage(e.getMessage());
        }
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
        } else view.showMessage("Введите корректный id");
    }
}
