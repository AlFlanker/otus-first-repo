package com.gmail.alexflanker89.Lesson_8_MongoDB.controller;

import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Book;
import com.gmail.alexflanker89.Lesson_8_MongoDB.domain.Comment;
import com.gmail.alexflanker89.Lesson_8_MongoDB.services.interfaces.BookService;
import com.gmail.alexflanker89.Lesson_8_MongoDB.services.interfaces.CommentService;
import com.gmail.alexflanker89.Lesson_8_MongoDB.services.interfaces.GenreService;
import com.gmail.alexflanker89.Lesson_8_MongoDB.view.View;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@ShellComponent
public class LibraryController {
    private final BookService bookService;
    private  final GenreService genreService;
    private final CommentService commentService;
    private final View view;
    /**
     * Поиск п оавтору
     *
     * @param name     Имя автора книги
     * @param lastname Фамилия автора книги
     */

    @ShellMethod(value = "Commands: author --n Name --l lastname", key = "book author")
    public void bookMethod(@ShellOption(value = {"--n", "name"}, defaultValue = "") java.lang.String name,
                           @ShellOption(value = {"--l", "lastname"}, defaultValue = "") java.lang.String lastname) {
        List<Book> books = bookService.getAllByAuthorsNameAndLastname(name, lastname);
        view.showBooks(books);
    }

    /**
     * Возвращает краткую инфу о всех книгах
     */
    @ShellMethod(value = "return all book", key = "book")
    public void getAllBook() {
        List<Book> all = bookService.getAll();
        view.showBooks(all);
    }

    /**
     * Возвращает книги по выбранному жанру
     *
     * @param line жанры книг через запятую
     */
    @ShellMethod(value = "Commands: --g genres", key = "book genre")
    public void genreMethod(@ShellOption(value = {"--g"}, defaultValue = "") java.lang.String line) {
        if(!StringUtils.isEmpty(line)){
            view.showBooks(bookService.getAllByGenres(line));
        } else view.showMessage("Введите корректное название жанра");
    }


    @ShellMethod(value = "return books author(-s)", key = "authors")
    public void getAllAuthor(@ShellOption(value = "--book_id") String id) {
        view.showAuthors(bookService.getByBookId(id));
    }

    /**
     * Возвращает жанры выбранной книги, если id =0, то все существующие жанры
     *
     * @param id книги
     */

    @ShellMethod(value = "Return all genres if book_id !=0, else return chose books genres ", key = "genres")
    public void getAllGenre(@ShellOption(value = "--book_id", defaultValue = "") String id) {
        view.showGenres(genreService.getAllGenreByBook(id));
    }

    /**
     * Оставить комментерий к книге
     *
     * @param id книги
     */
    @ShellMethod(value = "write comment ", key = "comment")
    public void writeComment(@ShellOption(value = "--book_id", defaultValue = "0") String id, @ShellOption(value = "--a") String username, @ShellOption(value = "--c") String text) {
        Comment comment = new Comment();
        comment.setUsername(username);
        comment.setComment(text);
        comment.setCreated(LocalDateTime.now());
        commentService.addComment(id,comment);
    }

    /**
     * Показать отзывы
     *
     * @param id книги
     */
    @ShellMethod(value = "show comment ", key = "comments")
    public void getComments(@ShellOption(value = "--book_id") String id) {
        view.showComments(commentService.getAllCommentByBookId(id));
    }
}
