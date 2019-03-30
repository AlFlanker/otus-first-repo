package com.gmail.alexflanker89.lesson5.controllers;

import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import com.gmail.alexflanker89.lesson5.execptions.GenreNotExistException;
import com.gmail.alexflanker89.lesson5.services.GenresService;
import com.gmail.alexflanker89.lesson5.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class SimpleGenreController implements GenreController {
    private final GenresService genresService;
    private final View view;
    //для наглядности!
    @Autowired
    public SimpleGenreController(GenresService genresService, View view) {
        this.genresService = genresService;
        this.view = view;
    }

    /**
     * Возвращает жанры выбранной книги, если id =0, то все существующие жанры
     * @param id книги
     */
    @ShellMethod(value = "Return all genres if id !=0, else return chose books genres ",key = "genres")
    public void getAllGenre(@ShellOption(value = "--book_id",defaultValue = "0") long id){
        try {
            if (id == 0) view.showGenres(genresService.getAll());
            else {
                Book book = new Book();
                book.setId(id);
                view.showGenres(genresService.getAllByBook(book));
            }
        }catch (GenreNotExistException e){
            view.showMessage(e.getMessage());
        }
    }
}
