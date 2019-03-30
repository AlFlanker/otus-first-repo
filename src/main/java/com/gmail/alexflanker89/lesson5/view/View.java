package com.gmail.alexflanker89.lesson5.view;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;

import java.util.List;

public interface View {
    void showGenres(List<Genre> genreList);
    void showBooks(List<Book> books);
    void showAuthors(List<Author> authors);
    void showMessage(String message);
    void showBook(Book book);
    void shortShowBook(Book book);
}
