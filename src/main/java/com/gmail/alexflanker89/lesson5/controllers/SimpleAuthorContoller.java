package com.gmail.alexflanker89.lesson5.controllers;

import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.execptions.AuthorNotExistException;
import com.gmail.alexflanker89.lesson5.services.AuthorsService;
import com.gmail.alexflanker89.lesson5.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class SimpleAuthorContoller {
    private final AuthorsService authorsService;
    private final View view;
    //для наглядности!
    @Autowired
    public SimpleAuthorContoller(AuthorsService authorsService, View view) {
        this.authorsService = authorsService;
        this.view = view;
    }

    /**
     * Возвращает всех авторов, если id =0
     * иначе возвращает авторов книги!
     * @param id книги
     */
    @ShellMethod(value = "return all author if id =0, else books author(-s)",key="authors")
    public void getAllAuthor(@ShellOption(value = "--book_id",defaultValue = "0") long id) {
        try {
            if (id == 0) {
                view.showAuthors(authorsService.getAll());
            } else {
                Book book = new Book();
                book.setId(id);
                view.showAuthors(authorsService.getAllByBook(book));

            }
        }
        catch (AuthorNotExistException e){
            view.showMessage(e.getMessage());
        }
    }


}
